package com.wdev.nfcchecker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wdev.nfcchecker.databinding.ItemListBinding
import com.wdev.nfcchecker.model.BrandItem
import com.wdev.nfcchecker.view.adapters.BrandItemsViewHolder

class ItemsAdapter(val data: List<BrandItem>): RecyclerView.Adapter<BrandItemsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandItemsViewHolder {
        val viewBinding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return BrandItemsViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holderBrand: BrandItemsViewHolder, position: Int) {
        holderBrand.binding.apply {
            name.text = data[position].name
            count.text = data[position].count.toString()
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
