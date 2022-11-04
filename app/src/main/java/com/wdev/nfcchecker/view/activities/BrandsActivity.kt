package com.wdev.nfcchecker.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.wdev.nfcchecker.databinding.ActivityBrandsBinding
import com.wdev.nfcchecker.view.adapters.BrandAdapter
import com.wdev.nfcchecker.viewmodel.ItemViewModel

class BrandsActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityBrandsBinding
    private val viewModel: ItemViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityBrandsBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        initLoadAds()

        setObservers()

        viewModel.getBrands(true)
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

    private fun setObservers() {
        viewModel.items.observe(this) {
            viewBinding.recyclerView?.apply {
                adapter = BrandAdapter(it) {
                    Intent(this@BrandsActivity, ModelsActivity::class.java).apply {
                        putExtra("brand", it)
                        startActivity(this)
                    }
                }
            }
        }

        viewModel.startGetBrands.observe(this) {
            viewBinding.animationView.visibility = View.VISIBLE
            viewModel.getBrands()
        }

        viewModel.brandsResponse.observe(this) {
            viewBinding.animationView.visibility = View.GONE
        }
    }
}