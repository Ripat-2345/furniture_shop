package com.example.furnitureshopsubmissiondicoding

import android.R
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.furnitureshopsubmissiondicoding.databinding.ItemRowFurnitureBinding


class ListFurnitureAdapter(private val listFurniture: ArrayList<Furniture>) : RecyclerView.Adapter<ListFurnitureAdapter.ListViewHolder>(){
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(var binding: ItemRowFurnitureBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): ListViewHolder {
            val binding = ItemRowFurnitureBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
            return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val(furnitureName: String, furniturePrice: String, furnitureDescription: String, furnitureImage: Int) = listFurniture[position]
        // using Glide for scaling bitmap image
        Glide.with(holder.itemView.context).load(furnitureImage).into(holder.binding.furnitureImage)
        holder.binding.furnitureName.text = furnitureName
        holder.binding.furniturePrice.text = furniturePrice
        holder.binding.furnitureDescription.text = furnitureDescription
        holder.itemView.setOnClickListener{onItemClickCallback.onItemClicked(listFurniture[holder.adapterPosition])}
    }

    override fun getItemCount(): Int = listFurniture.size

    // interface for click item
    interface OnItemClickCallback{
        fun onItemClicked(data: Furniture)
    }
}