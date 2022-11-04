package com.wdev.nfcchecker.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.wdev.nfcchecker.databinding.FragmentPreviewBinding
import com.wdev.nfcchecker.view.activities.BrandsActivity
import com.wdev.nfcchecker.view.adapters.BrandAdapter
import com.wdev.nfcchecker.viewmodel.ItemViewModel

class PreviewFragment : Fragment() {

    private lateinit var viewBinding: FragmentPreviewBinding
    private val viewModel: ItemViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        viewBinding = FragmentPreviewBinding.inflate(layoutInflater, container, false)
        viewBinding.moreButton.setOnClickListener {
            Intent(activity, BrandsActivity::class.java).apply {
                startActivity(this)
            }
        }

        initLoadAds()
        setObservers()

        return viewBinding.root
    }

    private fun setObservers() {
        viewModel.items.observe(viewLifecycleOwner) {
            viewBinding.recyclerView?.apply {
                adapter = BrandAdapter(it) {

                }
            }
        }

        viewModel.startGetBrands.observe(viewLifecycleOwner) {
            viewBinding.animationView.visibility = View.VISIBLE
            viewModel.getBrands()
        }

        viewModel.brandsResponse.observe(viewLifecycleOwner) {
            viewBinding.animationView.visibility = View.GONE
            viewBinding.contentBrandsLayout.visibility = View.VISIBLE
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