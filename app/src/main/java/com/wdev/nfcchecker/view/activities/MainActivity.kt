package com.wdev.nfcchecker.view.activities

import android.content.Context
import android.nfc.NfcManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.TelephonyManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.activity.viewModels
import com.wdev.nfcchecker.R
import com.wdev.nfcchecker.core.MyPreferences
import com.wdev.nfcchecker.view.fragments.PreviewFragment
import com.wdev.nfcchecker.databinding.ActivityMainBinding
import com.wdev.nfcchecker.viewmodel.ItemViewModel
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding
    private val viewModel: ItemViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(LayoutInflater.from(applicationContext))
        setContentView(viewBinding.root)

        setObservers()

        viewBinding.content.checkButton.setOnClickListener(::findInformation)
    }

    private fun setObservers() {
        viewModel.modelIsCreated.observe(this) {
            viewBinding.face.visibility = View.VISIBLE
            viewBinding.animationView.visibility = View.GONE
            viewModel.startGetBrands.postValue(true)
        }
    }

    private fun findInformation(view: View) {

        var nfcManager = getSystemService(NFC_SERVICE) as NfcManager
        val adapter = nfcManager.defaultAdapter

        var supported = false

        if (adapter != null && adapter.isEnabled) {
            supported = true
            viewBinding.message.text = getString(R.string.nfc_available)
            viewBinding.face.setImageDrawable(getDrawable(R.drawable.face_happy))
        } else if (adapter != null && !adapter.isEnabled) {
            supported = true
            viewBinding.message.text = getString(R.string.nfc_is_not_enabled)
        } else {
            viewBinding.message.text = getString(R.string.nfc_is_not_supported)
            viewBinding.face.setImageDrawable(getDrawable(R.drawable.face_sad))
        }

        viewBinding.device.text = "${Build.MANUFACTURER} - ${Build.MODEL}"

        var country = ""

        val tm = applicationContext.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        val simCountry = tm.simCountryIso
        if (simCountry != null && simCountry.length == 2) { // SIM country code is available
            country = simCountry.toLowerCase(Locale.US)
        } else if (tm.phoneType != TelephonyManager.PHONE_TYPE_CDMA) { // device is not 3G (would be unreliable)
            val networkCountry = tm.networkCountryIso
            if (networkCountry != null && networkCountry.length == 2) { // network country code is available
                country = networkCountry.toLowerCase(Locale.US)
            }
        }

        if (MyPreferences.isChecked(applicationContext)) {
            viewModel.getBrands()
        } else {
            viewBinding.face.visibility = View.GONE
            viewBinding.animationView.visibility = View.VISIBLE

            MyPreferences.setChecked(applicationContext)
            viewModel.setModel(
                Build.MANUFACTURER,
                Build.MODEL,
                country,
                if (supported) 1 else 0
            )
        }

        viewBinding.content.message.visibility = View.GONE
        viewBinding.content.checkButton.visibility = View.GONE
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(viewBinding.content.contentLayout.id, PreviewFragment())
        fragmentTransaction.commit()

    }
}