package com.tinyappco.kotlinhelp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.tinyappco.kotlinhelp.databinding.ActivityMainBinding
import java.net.URLEncoder

class MainActivity : AppCompatActivity() {

    private val WEB_BROWSE_REQUEST = 0
    private var prevUrl : String? = null

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnGoogle.setOnClickListener {
            loadWebActivity("https://google.co.uk/search?q=$encodedSearchTerm")
        }

        binding.btnStackOverflow.setOnClickListener {
            loadWebActivity("https://stackoverflow.com/search?q=$encodedSearchTerm")
        }

        binding.btnKotlin.setOnClickListener{
            loadWebActivity("https://kotlinlang.org/?q=$encodedSearchTerm&p=0")
        }

        binding.btnAndroid.setOnClickListener{
            loadWebActivity("https://developer.android.com/s/results/?q=$encodedSearchTerm")
        }

        binding.btnPrevious.setOnClickListener {
            loadWebActivity(prevUrl?: "")
        }

        binding.etSearch.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) { }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                toggleButtonsState(binding.etSearch.text.length > 1)
            }
        })

        toggleButtonsState(false)
    }

    private val encodedSearchTerm : String
        get() = URLEncoder.encode(binding.etSearch.text.toString(),"UTF-8")


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
                binding.btnPrevious.visibility = View.VISIBLE
            } else {
                binding.btnPrevious.visibility = View.INVISIBLE
            }
        }
    }

    private fun toggleButtonsState(enabled: Boolean){
        binding.btnAndroid.isEnabled = enabled
        binding.btnKotlin.isEnabled = enabled
        binding.btnStackOverflow.isEnabled = enabled
        binding.btnGoogle.isEnabled = enabled
    }
}
