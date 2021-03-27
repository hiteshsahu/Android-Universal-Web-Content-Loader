package com.c2h5oh.universal_webloader.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentTransaction
import com.c2h5oh.universal_webloader.R
import com.c2h5oh.universal_webloader.UniversalWebView

class SampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.replace(R.id.webView1, UniversalWebView.newInstance(VIMEO_DEMO))
        ft.replace(R.id.webView2, UniversalWebView.newInstance(YOU_TUBE_DEMO_URL))
        ft.commit()
    }

    companion object {
        // Demo URLS
        private const val VIMEO_DEMO = "https://vimeo.com/108521107"
        private const val YOU_TUBE_DEMO_URL = "https://www.youtube.com/watch?v=WUnX1ovoosw"

    }
}