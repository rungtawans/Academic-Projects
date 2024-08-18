package com.example.foodyapp

import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.esri.arcgisruntime.ArcGISRuntimeEnvironment
import com.esri.arcgisruntime.concurrent.ListenableFuture
import com.esri.arcgisruntime.geometry.Envelope
import com.esri.arcgisruntime.geometry.GeodeticCurveType
import com.esri.arcgisruntime.geometry.GeometryEngine
import com.esri.arcgisruntime.geometry.LinearUnit
import com.esri.arcgisruntime.geometry.LinearUnitId
import com.esri.arcgisruntime.geometry.Point
import com.esri.arcgisruntime.geometry.SpatialReferences
import com.esri.arcgisruntime.mapping.ArcGISMap
import com.esri.arcgisruntime.mapping.BasemapStyle
import com.esri.arcgisruntime.mapping.Viewpoint
import com.esri.arcgisruntime.mapping.view.DefaultMapViewOnTouchListener
import com.esri.arcgisruntime.mapping.view.Graphic
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay
import com.esri.arcgisruntime.mapping.view.IdentifyGraphicsOverlayResult
import com.esri.arcgisruntime.mapping.view.MapView
import com.esri.arcgisruntime.symbology.PictureMarkerSymbol
import com.esri.arcgisruntime.symbology.SimpleFillSymbol
import com.esri.arcgisruntime.symbology.SimpleLineSymbol
import com.example.foodyapp.Util.FirestoreRepository
import com.example.foodyapp.Util.FoodStoreItem
import com.example.foodyapp.Util.GlobalBox
import com.example.foodyapp.Util.getLoading
import kotlin.math.roundToInt


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MapFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MapFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var mapView: MapView
    private lateinit var graphicLayer: GraphicsOverlay

    private lateinit var houseGraphicLayer: GraphicsOverlay
    private var firebaseRepository = FirestoreRepository()
    private var foodStoreItemList: MutableList<FoodStoreItem> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mapView = view.findViewById(R.id.mapView)

        setApiKeyForApp()
        setupMap()
        addLayerGraphicOverlay()
        if (GlobalBox.selectedFoodStoreItem != null) {
            foodStoreItemList.add(GlobalBox.selectedFoodStoreItem!!)
            drawHouseDataOnMap()
            zoomToAllExtent()
        } else {
            addImagePointGraphic()
            createBufferArea()
            fetchDataFromDatabase()
        }
    }

    private fun createBufferArea() {
        val userLocationPoin = Point(100.53892960569901, 13.701952685466564, SpatialReferences.getWgs84())
        val geometryBuffer = GeometryEngine.bufferGeodetic(userLocationPoin, 3.0,
            LinearUnit(LinearUnitId.KILOMETERS), Double.NaN, GeodeticCurveType.GEODESIC)

        // create symbol for buffer geometry
        val geodesicOutlineSymbol = SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, Color.WHITE, 2F)
        // 0x4D00FF00 = Green Color with 25% Opacity (4D = 25%)
        val geodesicBufferFillSymbol = SimpleFillSymbol(SimpleFillSymbol.Style.SOLID,
            0x4D53E88B.toInt(), geodesicOutlineSymbol)

        // new graphic
        val graphicBuffer =  Graphic(geometryBuffer, geodesicBufferFillSymbol)
        graphicLayer.graphics.add(graphicBuffer)
    }

    private fun addImagePointGraphic() {
        // create a point geometry with a location and spatial reference
        // Point(latitude, longitude, spatial reference)
        val point = Point(100.53892960569901, 13.701952685466564, SpatialReferences.getWgs84())

        val pictureMarkerSymbol = PictureMarkerSymbol.createAsync(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.marker
            ) as BitmapDrawable
        ).get()

        // set width, height, z from ground
        pictureMarkerSymbol.height = 29f
        pictureMarkerSymbol.width = 23f
        pictureMarkerSymbol.offsetY = 0f
        // create a graphic with the point geometry and symbol
        val pointGraphic = Graphic(point, pictureMarkerSymbol)

        // add the point graphic to the graphics overlay
        graphicLayer.graphics.add(pointGraphic)
    }

    private fun fetchDataFromDatabase() {
        var dialog = getLoading()
        dialog.show()
        foodStoreItemList.clear()
        firebaseRepository.getFoodStoreFromDatabase().get().addOnSuccessListener { documents ->
            dialog.dismiss()
            for (document in documents) {
                val foodStoreData = document.toObject(FoodStoreItem::class.java)
                foodStoreItemList.add(foodStoreData)
            }
            drawHouseDataOnMap()
            zoomToAllExtent()
        }
    }

    private fun zoomToAllExtent() {
        val listOfPoint = mutableListOf<Point>()
        foodStoreItemList.forEach {
            val houseLocation = Point(it.long, it.lat, SpatialReferences.getWgs84())
            listOfPoint.add(houseLocation)
        }
        var mCompleteExtent: Envelope = GeometryEngine.combineExtents(listOfPoint);
        var newX1 = mCompleteExtent.xMin - mCompleteExtent.xMin*0.0001
        var newY1 = mCompleteExtent.yMin - mCompleteExtent.yMin*0.0001
        var newX2 = mCompleteExtent.xMax + mCompleteExtent.xMax*0.0001
        var newY2 = mCompleteExtent.yMax + mCompleteExtent.yMax*0.0001
        var mExtentPadding = Envelope(newX1, newY1, newX2, newY2, mCompleteExtent.spatialReference)
        mapView.setViewpointAsync(Viewpoint(mExtentPadding));
    }

    private fun drawHouseDataOnMap() {
        val pictureMarkerSymbol = PictureMarkerSymbol.createAsync(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.house_pin2
            ) as BitmapDrawable
        ).get()
        // set width, height, z from ground
        pictureMarkerSymbol.height = 36f
        pictureMarkerSymbol.width = 36f
        pictureMarkerSymbol.offsetY = 0f

        for (foodStoreData in foodStoreItemList) {
            // create a point geometry with a location and spatial reference
            // Point(latitude, longitude, spatial reference)
            val point = Point(foodStoreData.long, foodStoreData.lat, SpatialReferences.getWgs84())

            // create a graphic with the point geometry and symbol
            val pointGraphic = Graphic(point, pictureMarkerSymbol)

            // add attribute to graphic
            val pointAttribute = mutableMapOf<String, Any>()
            pointAttribute["location"] = foodStoreData.location
            pointAttribute["type"] = foodStoreData.type
            pointGraphic.attributes.putAll(pointAttribute)

            // add the point graphic to the graphics overlay
            houseGraphicLayer.graphics.add(pointGraphic)
        }

    }

    private fun addLayerGraphicOverlay() {
        // create a graphics overlay and add it to the map view
        graphicLayer = GraphicsOverlay()
        houseGraphicLayer = GraphicsOverlay()
        mapView.graphicsOverlays.add(graphicLayer)
        mapView.graphicsOverlays.add(houseGraphicLayer)
    }

    private fun setupMap() {
        // create a map with the BasemapStyle streets
        val map = ArcGISMap(BasemapStyle.ARCGIS_TOPOGRAPHIC)
        // set the map to be displayed in the layout's MapView
        mapView.map = map

        // set the viewpoint, Viewpoint(latitude, longitude, scale)
        mapView.setViewpoint(Viewpoint(13.700547, 100.535619, 72000.0))

        mapView.apply {
            onTouchListener = object : DefaultMapViewOnTouchListener(requireContext(), this) {
                override fun onSingleTapConfirmed(motionEvent: MotionEvent): Boolean {
                    mapView.callout.dismiss()
                    // get the point that was tapped on the screen
                    val screenPoint =
                        android.graphics.Point(motionEvent.x.roundToInt(), motionEvent.y.roundToInt())
                    // create a map point from that screen point
                    val mapPoint = mapView.screenToLocation(screenPoint)
                    val identifyGraphics: ListenableFuture<IdentifyGraphicsOverlayResult> =
                        mapView.identifyGraphicsOverlayAsync(
                            houseGraphicLayer,
                            screenPoint,
                            10.0,
                            false
                        )
                    val results = identifyGraphics.get()
                    if (results.graphics.size > 0) {
                        val graphicData = results.graphics[0]
                        val locationData = graphicData.attributes.get("location").toString()
                        val typeData = graphicData.attributes.get("type").toString()
                        // create a textview for the callout
                        val calloutContent = TextView(requireContext()).apply {
                            setTextColor(Color.BLACK)
                            setSingleLine()

                            // format coordinates to 4 decimal places and display lat long read out
                            text = String.format("type: %s, %s", typeData, locationData)
                        }

                        // get the callout, set its content and show it and the tapped location
                        mapView.callout.apply {
                            location = mapPoint
                            content = calloutContent
                            show()
                        }

                        // center the map on the tapped location
                        mapView.setViewpointCenterAsync(mapPoint)
                    }

                    performClick()
                    return super.onSingleTapConfirmed(motionEvent)
                }
            }
        }
    }

    private fun setApiKeyForApp() {
        // set your API key
        // Note: it is not best practice to store API keys in source code. The API key is referenced
        // here for the convenience of this tutorial.

        ArcGISRuntimeEnvironment.setApiKey("AAPKf7e103cf14a1420a92908bd930fc8784yxCnbmTf958bPG4LheRWoRuKtg_3KKfHfofHJc3WCma1G_N2uofcHlBpPqCTPZgv")
        ArcGISRuntimeEnvironment.setLicense("runtimelite,1000,rud8473982436,none,KGE60RFLTH0D003AD189")
    }

    override fun onPause() {
        super.onPause()
        mapView.pause()
    }

    override fun onResume() {
        super.onResume()
        mapView.resume()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.dispose()
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MapFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MapFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}