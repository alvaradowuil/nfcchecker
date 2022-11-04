package com.wdev.nfcchecker.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wdev.nfcchecker.databinding.BrandItemListBinding
import com.wdev.nfcchecker.model.BrandItem

class BrandAdapter(val data: List<BrandItem>, val onClick: (BrandItem) -> Unit): RecyclerView.Adapter<BrandItemsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandItemsViewHolder {
        val viewBinding = BrandItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return BrandItemsViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holderBrand: BrandItemsViewHolder, position: Int) {
        holderBrand.binding.apply {
            name.text = data[position].name
            count.text = data[position].quantity.toString()
            holderBrand.itemView.setOnClickListener {
                onClick(data[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
