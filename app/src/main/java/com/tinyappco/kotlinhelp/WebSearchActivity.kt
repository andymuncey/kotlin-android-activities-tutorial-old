package com.tinyappco.kotlinhelp

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_web_search.*
import kotlinx.android.synthetic.main.fragment_web.*

class WebSearchActivity : AppCompatActivity() {


    lateinit var webFrag : WebFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_search)


        var url = intent.getStringExtra("url")

        if (savedInstanceState != null) {
            url = savedInstanceState.getString("url")
        }

        webFrag = webFragment as WebFragment
                webFrag.loadUrl(url)

    }

    override fun onBackPressed() {
        val intent = Intent()
        intent.putExtra("url",webFrag.webView.url)
        setResult(Activity.RESULT_OK, intent)
        finish()
        super.onBackPressed()
    }
}
