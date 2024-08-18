package com.example.foodyapp.Util

import com.ismaeldivita.chipnavigation.ChipNavigationBar

object GlobalBox {
    // put everything we want to save here...
    var savedBottomNavigation: ChipNavigationBar? = null
    var savedFoodStoreListItem: MutableList<FoodStoreItem> = mutableListOf()
    var selectedFoodStoreItem: FoodStoreItem? = null
}