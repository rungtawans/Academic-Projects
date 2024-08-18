package com.example.foodyapp.Util

data class FoodStoreItem(
    val location: String = "",
    val price: String = "",
    val long: Double = Double.NaN,
    val lat: Double = Double.NaN,
    val type: String = "",
    val image_url: String = "")