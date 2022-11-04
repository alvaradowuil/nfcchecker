package com.wdev.nfcchecker.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wdev.nfcchecker.R
import com.wdev.nfcchecker.databinding.ModelItemListBinding
import com.wdev.nfcchecker.model.ModelItem

class ModelAdapter(val data: List<ModelItem>, val onClick: (ModelItem) -> Unit): RecyclerView.Adapter<ModelItemsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelItemsViewHolder {
        val viewBinding = ModelItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ModelItemsViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holderBrand: ModelItemsViewHolder, position: Int) {
        val stringFormat = holderBrand.itemView.context.getString(R.string.records_without_nfc)
        holderBrand.binding.apply {

            name.text = data[position].name
            supportedCount.text = data[position].supported.toString()

            val unsupported = data[position].unsupported
            if (unsupported > 0) {
                unsupportedCount.text = String.format(stringFormat, unsupported.toString())
            }

            holderBrand.itemView.setOnClickListener {
                onClick(data[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
