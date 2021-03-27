package com.c2h5oh.universal_webloader.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.c2h5oh.universal_webloader.R
import com.c2h5oh.universal_webloader.UniversalWebView

class ViewPagerDemoActivity : AppCompatActivity() {

    private var toolbar: Toolbar? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample)

        //ToolBar
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        //View Pager
        findViewById<ViewPager>(R.id.container)?.apply {
            adapter = SectionsPagerAdapter(supportFragmentManager)

            addOnPageChangeListener(object : OnPageChangeListener {
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                }

                override fun onPageSelected(position: Int) {
                    when (position) {
                        0 -> toolbar?.title = "Youtube Demo"
                        1 -> toolbar?.title = "Vimeo Demo"
                        2 -> toolbar?.title = "PayUMoney Demo"
                        3 -> toolbar?.title = "PDF Demo"
                        else -> toolbar?.title = "Google Search Demo"
                    }
                }

                override fun onPageScrollStateChanged(state: Int) {}
            })
        }
    }

    inner class SectionsPagerAdapter(fragmentManager: FragmentManager?) :
        FragmentPagerAdapter(fragmentManager!!, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> UniversalWebView.newInstance(YOU_TUBE_DEMO_URL)
                1 -> UniversalWebView.newInstance(VIMEO_DEMO_URL)
                2 -> UniversalWebView.newInstance(PAYU_MONEY_URL)
                3 -> UniversalWebView.newInstance(
                    "http://docs.google.com/viewer?url=" + PDF_URL
                )
                else -> UniversalWebView.newInstance("Android", true)
            }
        }

        override fun getCount(): Int { return 5 }
    }

    companion object {
        // Demo URLS
        private const val VIMEO_DEMO_URL = "https://vimeo.com/108521107"
        private const val YOU_TUBE_DEMO_URL = "https://www.youtube.com/watch?v=WUnX1ovoosw"
        private const val PAYU_MONEY_URL = "https://www.netflix.com/browse"
        private const val PDF_URL =
            "http://partners.adobe.com/public/developer/en/acrobat/PDFOpenParameters.pdf"
    }
}