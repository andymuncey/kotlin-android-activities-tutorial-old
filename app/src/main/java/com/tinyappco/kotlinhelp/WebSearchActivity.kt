package com.tinyappco.kotlinhelp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.tinyappco.kotlinhelp.databinding.ActivityWebSearchBinding


class WebSearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWebSearchBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)
//        binding.webView.webViewClient = WebViewClient() //prevents opening in browser app
//        binding.webView.settings.javaScriptEnabled = true // required for search functionality on Kotlin and Android sites

        var url = intent.getStringExtra("url")

        if (savedInstanceState != null) {
            url = savedInstanceState.getString("url")
        }

//        binding.webView.loadUrl(url!!) //should never be null

        val fragment = supportFragmentManager.findFragmentById(R.id.webFragmentContainerView) as WebSearchFragment
        fragment.loadUrl(url!!)



        onBackPressedDispatcher.addCallback(this,object:OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                val intent = Intent()
                //val fragment = supportFragmentManager.findFragmentById(R.id.webFragmentContainerView) as WebSearchFragment

                intent.putExtra("url",fragment.getCurrentUrl())
                setResult(Activity.RESULT_OK, intent)
                finish()
            }

        })

    }







//    override fun onBackPressed() {
//        val intent = Intent()
//        intent.putExtra("url",binding.webView.url)
//        setResult(Activity.RESULT_OK, intent)
//        finish()
//        super.onBackPressed()
//    }
}
