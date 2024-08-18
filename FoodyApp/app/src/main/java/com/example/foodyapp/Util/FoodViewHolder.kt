package com.example.foodyapp.Util

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodyapp.R

class FoodViewHolder(view: View) : RecyclerView.ViewHolder(view)  {
    private val imageView = view.findViewById<ImageView>(R.id.ivHouse)
    private val imageViewActionButton = view.findViewById<ImageView>(R.id.ivActionBtn)
    private val textViewHouseDetail = view.findViewById<TextView>(R.id.tvHouseDetail)
    private val textViewPrice = view.findViewById<TextView>(R.id.tvPriceTag)
    private val textViewType = view.findViewById<TextView>(R.id.tvFoodType)
    private val context = view.context

    fun bind(foodStoreItem: FoodStoreItem) {
        Glide.with(context).load(foodStoreItem.image_url).into(imageView);
        textViewHouseDetail.text = foodStoreItem.location
        textViewPrice.text = foodStoreItem.price
        textViewType.text = foodStoreItem.type

        imageViewActionButton.setOnClickListener {
            var clickedData = GlobalBox.savedFoodStoreListItem.first {
                it.location == foodStoreItem.location
            }
            GlobalBox.selectedFoodStoreItem = clickedData
            GlobalBox.savedBottomNavigation?.setItemSelected(R.id.mapPage)
        }
    }
}