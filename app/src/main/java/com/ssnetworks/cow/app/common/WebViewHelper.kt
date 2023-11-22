package com.ssnetworks.cow.app.common

import android.annotation.SuppressLint
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient

@SuppressLint("SetJavaScriptEnabled")
class WebViewHelper(
    private var webView: WebView,
    webViewClient: WebViewClient
) {

    init {
        webView.webViewClient = webViewClient
        webView.webChromeClient = WebChromeClient()

        // WebView 화면크기에 맞추도록 설정 - setUseWideViewPort 와 같이 써야함
        webView.settings.loadWithOverviewMode = true

        // wide viewport 설정 - setLoadWithOverviewMode 와 같이 써야함
        webView.settings.useWideViewPort = true

        // 줌 설정 여부
        webView.settings.setSupportZoom(false)

        // 줌 확대/축소 버튼 여부
        webView.settings.builtInZoomControls = false

        // 자바스크립트 사용여부
        webView.settings.javaScriptEnabled = true

        // javascript가 window.open()을 사용할 수 있도록 설정
        webView.settings.javaScriptCanOpenWindowsAutomatically = true

        // 멀티 윈도우 사용 여부
        webView.settings.setSupportMultipleWindows(true)

        // 로컬 스토리지 (localStorage) 사용여부
        webView.settings.domStorageEnabled = true
    }

    fun navigate(url: String) {
        // 웹페이지 호출
        webView.loadUrl(url)
    }
}