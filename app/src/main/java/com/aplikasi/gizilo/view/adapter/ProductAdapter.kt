package com.aplikasi.gizilo.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import  androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.aplikasi.gizilo.data.response.GetProductResponseItem
import com.aplikasi.gizilo.databinding.ItemRowBinding
import com.bumptech.glide.Glide

class ProductAdapter : ListAdapter<GetProductResponseItem, ProductAdapter.MyViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val list = getItem(position)
        holder.bind(list)
    }
    class MyViewHolder(private val binding: ItemRowBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(list: GetProductResponseItem) {
            binding.nameProduct.text = list.name
            binding.weightProduct.text = list.weight.toString()+" g"
            Glide.with(itemView.context).load(list.images).into(binding.imgProduct)
        }
    }
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<GetProductResponseItem>() {
            override fun areItemsTheSame(
                oldItem: GetProductResponseItem,
                newItem: GetProductResponseItem
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: GetProductResponseItem,
                newItem: GetProductResponseItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}