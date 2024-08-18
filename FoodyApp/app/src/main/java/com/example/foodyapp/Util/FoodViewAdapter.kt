package com.example.foodyapp.Util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodyapp.R

class FoodViewAdapter (private val foodStoreItemList: List<FoodStoreItem>) : RecyclerView.Adapter<FoodViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.food_template, parent, false)
        return FoodViewHolder(view)
    }

    override fun getItemCount(): Int {
        return foodStoreItemList.size
    }

    override fun onBindViewHolder(viewHolder: FoodViewHolder, currentPage: Int) {
        val viewItem = foodStoreItemList[currentPage]
        viewHolder.bind(viewItem)
    }
}