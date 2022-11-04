package com.alvarado.chucknorrispractice.data

import com.wdev.nfcchecker.model.BaseResponse
import com.wdev.nfcchecker.model.BrandsResponse
import com.wdev.nfcchecker.model.ModelResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiMethods {
    @GET("api/brands")
    suspend fun getBrands(
        @Header("Authorization") token: String
    ): Response<BrandsResponse>

    @GET("api/models")
    suspend fun getModels(
        @Query("brandId") brandId: Int,
        @Header("Authorization") token: String
    ): Response<ModelResponse>

    @POST("api/model/create")
    suspend fun createModel(
        @Query("brandName") brandName: String,
        @Query("modelName") modelName: String,
        @Query("country") country: String,
        @Query("supported") supported: Int,
        @Header("Authorization") token: String
    ): Response<BaseResponse>
}