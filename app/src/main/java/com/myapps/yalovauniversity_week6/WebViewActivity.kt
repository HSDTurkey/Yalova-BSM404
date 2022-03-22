package com.myapps.yalovauniversity_week6

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.myapps.yalovauniversity_week6.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebViewBinding
    private var instagramLink: String? = null
    private var twitterLink: String? = null
    private var linkedInLink: String? = null
    private var socialMediaLink: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)

        instagramLink = intent.getStringExtra("Instagram").toString()
        twitterLink = intent.getStringExtra("Twitter").toString()
        linkedInLink = intent.getStringExtra("LinkedIn").toString()

        socialMediaLink = when {
            instagramLink != "null" -> {
                instagramLink
            }
            twitterLink != "null" -> {
                twitterLink
            }
            else -> {
                linkedInLink
            }
        }

        webViewSetup(socialMediaLink)

        setContentView(binding.root)

    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun webViewSetup(url: String?) {

        binding.webView.apply {
            webViewClient = WebViewClient()
            loadUrl(url!!)

            settings.javaScriptEnabled = true
            settings.safeBrowsingEnabled = true
            settings.domStorageEnabled = true
        }
    }

    override fun onBackPressed() {
        if (binding.webView.canGoBack()) binding.webView.goBack() else super.onBackPressed()
    }
}