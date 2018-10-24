package com.tinyappco.kotlinhelp

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.net.URLEncoder

class MainActivity : AppCompatActivity() {

    private val WEB_BROWSE_REQUEST = 0
    private var prevUrl : String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnGoogle.setOnClickListener {
            loadWebActivity("https://google.co.uk/search?q=$encodedSearchTerm")
        }

        btnStackOverflow.setOnClickListener {
            loadWebActivity("https://stackoverflow.com/search?q=$encodedSearchTerm")
        }

        btnKotlin.setOnClickListener{
            loadWebActivity("https://kotlinlang.org/?q=$encodedSearchTerm&p=0")
        }

        btnAndroid.setOnClickListener{
            loadWebActivity("https://developer.android.com/s/results/?q=$encodedSearchTerm")
        }

        btnPrevious.setOnClickListener {
            loadWebActivity(prevUrl?: "")
        }

        etSearch.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) { }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                toggleButtonsState(etSearch.text.length > 1)
            }
        })

        toggleButtonsState(false)
    }

    private val encodedSearchTerm : String
        get() = URLEncoder.encode(etSearch.text.toString(),"UTF-8")


    private fun loadWebActivity(url: String) {
        val intent = Intent(this, WebSearchActivity::class.java)
        intent.putExtra("url", url)
        startActivityForResult(intent,WEB_BROWSE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            val url = data?.getStringExtra("url")
            if (url != null) {
                prevUrl = url
                btnPrevious.visibility = View.VISIBLE
            } else {
                btnPrevious.visibility = View.INVISIBLE
            }
        }
    }

    private fun toggleButtonsState(enabled: Boolean){
        btnAndroid.isEnabled = enabled
        btnKotlin.isEnabled = enabled
        btnStackOverflow.isEnabled = enabled
        btnGoogle.isEnabled = enabled
    }
}
