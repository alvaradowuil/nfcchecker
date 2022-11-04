package com.wdev.nfcchecker.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.wdev.nfcchecker.R
import com.wdev.nfcchecker.databinding.ActivityModelsBinding
import com.wdev.nfcchecker.model.BrandItem
import com.wdev.nfcchecker.view.adapters.BrandAdapter
import com.wdev.nfcchecker.view.adapters.ModelAdapter
import com.wdev.nfcchecker.viewmodel.ItemViewModel

class ModelsActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityModelsBinding
    private val viewModel: ItemViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityModelsBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        initLoadAds()

        val brand = intent.extras?.get("brand") as BrandItem

        setObservers()

        viewModel.getModels(brand.brandId)
        viewBinding.title.text = getString(R.string.registered_models_title, brand.name)
    }

    private fun setObservers() {
        viewModel.modelItems.observe(this) {
            viewBinding.recyclerView?.apply {
                adapter = ModelAdapter(it) {

                }
            }
        }

        viewModel.modelsResponse.observe(this) {
            viewBinding.animationView.visibility = View.GONE
        }
    }

    private fun initLoadAds() {
        val adRequest = AdRequest.Builder().build()
        viewBinding.banner.loadAd(adRequest)

        viewBinding.banner.adListener = object : AdListener() {
            override fun onAdClicked() {
                super.onAdClicked()
            }

            override fun onAdClosed() {
                super.onAdClosed()
            }

            override fun onAdFailedToLoad(p0: LoadAdError) {
                super.onAdFailedToLoad(p0)
            }

            override fun onAdImpression() {
                super.onAdImpression()
            }

            override fun onAdLoaded() {
                super.onAdLoaded()
            }

            override fun onAdOpened() {
                super.onAdOpened()
            }
        }
    }
}