package com.wdev.nfcchecker.model

import com.google.gson.annotations.SerializedName

data class ModelResponse(
    @SerializedName("error")
    val error: Boolean,

    @SerializedName("message")
    val message: String,

    @SerializedName("data")
    val data: ArrayList<ModelItem>
)
