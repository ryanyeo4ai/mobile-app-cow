package com.ssnetworks.cow.app

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatDelegate
import com.google.firebase.messaging.FirebaseMessaging
import com.ssnetworks.cow.app.base.BaseActivity
import com.ssnetworks.cow.app.common.WebViewHelper
import com.ssnetworks.cow.app.databinding.ActivityMainBinding


class MainActivity : BaseActivity() {
    private val tag: String = MainActivity::class.java.simpleName

    private lateinit var binding: ActivityMainBinding
    private var webView: WebViewHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        initUi()
        initData()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initUi() {
        binding.progressBar.animate()

        webView = WebViewHelper(binding.webView, WebClient())
        webView?.navigate("https://web.ssnetworks.kr")
    }

    private fun initData() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener {
            val fcmToken = it.result
            Log.e(tag, "Firebase Token: $fcmToken")

            Handler(Looper.getMainLooper()).postDelayed({
                val jsUrl = "javascript:fcmToken('$fcmToken')"
                Log.e("MainActivity", "fcmTokenLoop url:$jsUrl")
                webView?.navigate(jsUrl)
            }, 3000)
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (binding.webView.canGoBack()) {
                    binding.webView.goBack()
                } else {
                    finish()
                }
            }
        })
    }

    inner class WebClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            return super.shouldOverrideUrlLoading(view, request)
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            Log.e("MainActivity", "onPageFinished...............")

            binding.progressBar.visibility = View.GONE
        }
    }
}