package com.wdev.nfcchecker.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class BrandItem(
    @SerializedName("brandid")
    val brandId: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("quantity")
    val quantity: Int,
) : Serializable