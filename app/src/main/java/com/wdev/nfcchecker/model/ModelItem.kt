package com.wdev.nfcchecker.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ModelItem(
    @SerializedName("brandid")
    val brandId: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("supported")
    val supported: Int,

    @SerializedName("unsupported")
    val unsupported: Int,
) : Serializable