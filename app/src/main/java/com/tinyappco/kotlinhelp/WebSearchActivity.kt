package com.tinyappco.kotlinhelp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_web_search.*
import kotlinx.android.synthetic.main.fragment_web.*

class WebSearchActivity : AppCompatActivity() {


    private lateinit var webFrag : WebFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_search)


        var url = intent.getStringExtra("url")

        if (savedInstanceState != null) {
            url = savedInstanceState.getString("url")
        }

        webFrag = webFragment as WebFragment
        if (url != null) {
            webFrag.loadUrl(url)
        }

    }

    override fun onBackPressed() {
        val intent = Intent()
        intent.putExtra("url",webFrag.webView.url)
        setResult(Activity.RESULT_OK, intent)
        finish()
        super.onBackPressed()
    }
}
