package com.tinyappco.kotlinhelp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_web_search.*

class WebSearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_search)
        webView.webViewClient = WebViewClient() //prevents opening in browser app
        webView.settings.javaScriptEnabled = true // required for search functionality on Kotlin and Android sites

        var url = intent.getStringExtra("url")

        if (savedInstanceState != null) {
            url = savedInstanceState.getString("url")
        }

        webView.loadUrl(url!!) //should never be null
    }

    override fun onBackPressed() {
        val intent = Intent()
        intent.putExtra("url",webView.url)
        setResult(Activity.RESULT_OK, intent)
        finish()
        super.onBackPressed()
    }
}
