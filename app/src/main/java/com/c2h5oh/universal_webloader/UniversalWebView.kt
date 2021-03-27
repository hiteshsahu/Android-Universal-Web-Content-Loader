package com.c2h5oh.universal_webloader

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import android.webkit.WebChromeClient.CustomViewCallback
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

/*
 * This fragment work as in App browser and handle web page loading in your app.
 * All you need to do is drop this fragment in your project along with universal_web_view.xml & video_progress.xml
 */
class UniversalWebView : Fragment() {

    //States
    private val PAGE_STARTED = 0x1
    private val PAGE_REDIRECTED = 0x2
    private var webViewPreviousState = 0

    //Clients
    private lateinit var mWebChromeClient: MyWebChromeClient
    private lateinit var mWebViewClient: MyWebViewClient

    // Views
    private var rootView: View? = null
    private var webView: WebView? = null
    private var mCustomView: View? = null
    private var loadingView: ProgressBar? = null
    private var customViewContainer: FrameLayout? = null
    private var customViewCallback: CustomViewCallback? = null

    // URL
    private var currentWebURL: String = GOOGLE_SERACH_URL

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        arguments?.apply {
            val urlToLoad = getString(WEB_URL_TO_LOAD)
            if (null != urlToLoad) currentWebURL = urlToLoad
        }

        rootView =  inflater.inflate(R.layout.universal_web_view, container, false)?.apply {
            customViewContainer = findViewById<View>(R.id.customViewContainer) as FrameLayout
            webView = findViewById<View>(R.id.webView) as WebView
            loadingView = findViewById<ProgressBar>(R.id.progressBar) as ProgressBar

            activity?.let {

                webView?.apply {
                    mWebViewClient = MyWebViewClient(it)
                    mWebChromeClient = MyWebChromeClient(it)

                    webViewClient = mWebViewClient
                    webChromeClient = mWebChromeClient
                    settings.javaScriptEnabled = true

                    // Important for PayUMoney
                    settings.domStorageEnabled = true

                    //Rest of WebView Settings
                    settings.setAppCacheEnabled(true)
                    settings.builtInZoomControls = true
                    settings.saveFormData = true
                    requestFocus()

                    loadWebUrl(currentWebURL)
                }
            }
        }

        return  rootView
    }

    private fun loadWebUrl(urlLoad: String) {
        webView?.loadUrl(urlLoad)
        if (!isConnected(activity)) {
            showToast("You are Offline")
        }
    }

    private fun inCustomView(): Boolean {
        return mCustomView != null
    }

    private fun hideCustomView() {
        mWebChromeClient?.onHideCustomView()
    }

    override fun onPause() {
        super.onPause()
        webView?.onPause()
    }

    override fun onResume() {
        super.onResume()
        webView?.onResume()
    }

    override fun onStop() {
        super.onStop()
        if (inCustomView()) {
            hideCustomView()
        }
    }

    internal inner class MyWebChromeClient(private val activityContext: Activity) :
        WebChromeClient() {
        private var mVideoProgressView: View? = null
        override fun onShowCustomView(
            view: View,
            requestedOrientation: Int,
            callback: CustomViewCallback
        ) {
            onShowCustomView(view, callback) // To change body of overridden
        }

        override fun onShowCustomView(view: View, callback: CustomViewCallback) {
            // if a view already exists then immediately terminate the new one
            if (mCustomView != null) {
                callback.onCustomViewHidden()
                return
            }
            mCustomView = view
            webView?.visibility = View.GONE
            customViewContainer?.visibility = View.VISIBLE
            customViewContainer?.addView(view)
            customViewCallback = callback
        }

        override fun getVideoLoadingProgressView(): View? {
            if (mVideoProgressView == null) {
                mVideoProgressView =
                    LayoutInflater.from(activityContext).inflate(R.layout.video_progress, null)
            }
            return mVideoProgressView
        }

        override fun onHideCustomView() {
            super.onHideCustomView()
            if (mCustomView == null) return
            webView?.visibility = View.VISIBLE
            customViewContainer?.visibility = View.GONE

            // Hide the custom view.
            mCustomView?.visibility = View.GONE

            // Remove the custom view from its container.
            customViewContainer?.removeView(mCustomView)
            customViewCallback?.onCustomViewHidden()
            mCustomView = null
        }
    }

    internal inner class MyWebViewClient(private val activityContext: Activity) :
        WebViewClient() {

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            webViewPreviousState = PAGE_STARTED
            loadingView?.visibility = View.VISIBLE
        }

        @RequiresApi(Build.VERSION_CODES.M)
        override fun onReceivedError(
            view: WebView?,
            request: WebResourceRequest?,
            error: WebResourceError?
        ) {
            super.onReceivedError(view, request, error)
            showToast(error?.description.toString())
        }

        override fun onReceivedHttpError(
            view: WebView?,
            request: WebResourceRequest?,
            errorResponse: WebResourceResponse?
        ) {
            loadingView?.visibility = View.GONE
            showToast(errorResponse?.reasonPhrase.toString())
            super.onReceivedHttpError(view, request, errorResponse)
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            if (webViewPreviousState == PAGE_STARTED) {
                loadingView?.visibility = View.GONE

            }
        }
    }

    private fun showToast(message: String) {
        rootView?.let {
            Snackbar.make(it, message, Snackbar.LENGTH_INDEFINITE)
                .setAction("Reload") { loadWebUrl(currentWebURL) }.show()
        }
    }

    companion object {
        const val WEB_URL_TO_LOAD = "webURLToLoad"
        private const val GOOGLE_SERACH_URL = "https://www.google.com/search?q="
        fun newInstance(
            webUrl: String,
            searchOnWeb: Boolean = false
        ): UniversalWebView {
            val bundle = Bundle()
            if (searchOnWeb) {
                // Search on google for query
                bundle.putString(WEB_URL_TO_LOAD, GOOGLE_SERACH_URL + webUrl)
            } else {
                // simply load url
                bundle.putString(WEB_URL_TO_LOAD, webUrl)
            }
            val newInstance = UniversalWebView()
            newInstance.arguments = bundle
            return newInstance
        }

        fun isConnected(context: Context?): Boolean {
            (context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).apply {
                val info = activeNetworkInfo
                return info != null && info.isConnected
            }
        }
    }
}