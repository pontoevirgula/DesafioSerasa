package com.chslcompany.desafioserasa.ui.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chslcompany.desafioserasa.databinding.ItemAdapterBinding
import com.chslcompany.desafioserasa.domain.model.Results

class FavoritesAdapter (private val longItemClick: ((result: Results) -> Unit)) :
    ListAdapter<Results, FavoritesAdapter.AdapterViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        val itemAdapterBinding = ItemAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AdapterViewHolder(itemAdapterBinding, longItemClick)
    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class AdapterViewHolder(
        private val itemAdapterBinding: ItemAdapterBinding,
        private val longItemClick: (result: Results) -> Unit
    ) :
        RecyclerView.ViewHolder(itemAdapterBinding.root) {


        fun bind(results: Results) {
            itemAdapterBinding.run {
                Glide.with(itemView)
                    .load("${results.thumbnail.path}.${results.thumbnail.extension}")
                    .fitCenter()
                    .into(imgPoster)

                txtTitle.text = results.name
                itemView.setOnLongClickListener {
                    longItemClick.invoke(results)
                    return@setOnLongClickListener true
                }

            }

        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Results>() {
            override fun areItemsTheSame(oldItem: Results, newItem: Results): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Results, newItem: Results): Boolean {
                return oldItem == newItem
            }

        }
    }
}