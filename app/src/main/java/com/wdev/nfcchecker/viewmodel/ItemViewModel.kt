package com.wdev.nfcchecker.viewmodel

import android.icu.text.AlphabeticIndex
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alvarado.chucknorrispractice.data.ApiMethods
import com.alvarado.chucknorrispractice.data.ClientHttp
import com.wdev.nfcchecker.model.BrandItem
import com.wdev.nfcchecker.model.ModelItem
import kotlinx.coroutines.launch

class ItemViewModel : ViewModel() {

    private val url = "https://nfc-checker.evermolina.com/"

    val items = MutableLiveData<ArrayList<BrandItem>>()
    val modelItems = MutableLiveData<ArrayList<ModelItem>>()
    val modelIsCreated = MutableLiveData<Boolean>()
    val brandsResponse = MutableLiveData<Boolean>()
    val startGetBrands = MutableLiveData<Boolean>()
    val modelsResponse = MutableLiveData<Boolean>()

    fun getBrands(allBrands: Boolean = false) {
        viewModelScope.launch {
            val result = ClientHttp.getRetrofit(url)
                .create(ApiMethods::class.java)
                .getBrands("Bearer 4|ny69NzLfyZngnJqQE086lExarAEBel277fkl7Doc")
            brandsResponse.postValue(true)
            if (result.isSuccessful) {
                result.body()?.let {
                    if (!it.error) {
                        if (allBrands) {
                            items.postValue(it.data)
                        } else {
                            items.postValue(ArrayList(it.data.take(2)))
                        }
                    }
                }
            }
        }
    }

    private fun AlphabeticIndex.Record.toOrderItemJson() = OrderItemJson(
        orderNumber = this[Tables.ENT_USAGE_BILLING_LINES_LOG.ORDER_NUMBER],
        product2ID = this[Tables.ENT_USAGE_BILLING_LINES_LOG.PRODUCT2_ID],
        sku = this[Tables.ENT_USAGE_BILLING_LINES_LOG.SKU],
        quantity = this[Tables.ENT_USAGE_BILLING_LINES_LOG.QUANTITY],
        unitPrice = this[Tables.ENT_USAGE_BILLING_LINES_LOG.NET_PRICE].toMonetaryValue(),
        priceBookEntryId = this[Tables.ENT_USAGE_BILLING_LINES_LOG.PRICE_BOOK_ENTRY_ID],
        periodStartDate = try { formatter.format(this[Tables.ENT_USAGE_BILLING_LINES_LOG.LAST_BILL_RUN_DATE].toLocalDate()) } catch (e: Exception) {null},
        periodEndDate = formatter.format(this[Tables.ENT_USAGE_BILLING_LINES_LOG.BILL_RUN_DATE].toLocalDate())



    )

    fun getModels(brandId: Int) {
        viewModelScope.launch {
            val result = ClientHttp.getRetrofit(url)
                .create(ApiMethods::class.java)
                .getModels(brandId,"Bearer 4|ny69NzLfyZngnJqQE086lExarAEBel277fkl7Doc")
            modelsResponse.postValue(true)
            if (result.isSuccessful) {
                result.body()?.let {
                    if (!it.error) {
                        modelItems.postValue(it.data)
                    } else {

                    }
                }
            }
        }
    }

    fun setModel(brandName: String, modelName: String, country: String, supported: Int) {
        viewModelScope.launch {
            val result = ClientHttp.getRetrofit(url)
                .create(ApiMethods::class.java)
                .createModel(brandName, modelName, country, supported,"Bearer 4|ny69NzLfyZngnJqQE086lExarAEBel277fkl7Doc")


            modelIsCreated.postValue(true)

            if (result.isSuccessful) {
                result.body()?.let {
                    /*if (!it.error) {

                    } else {
                        items.postValue(it.data)
                    }*/
                }
            }
        }
    }
}