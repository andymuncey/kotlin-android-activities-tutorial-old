package com.tinyappco.kotlinhelp

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_web.*


class WebFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_web, container, false)
    }


    @SuppressLint("SetJavaScriptEnabled")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        webView.webViewClient = WebViewClient() //prevents opening in browser app
        webView.settings.javaScriptEnabled = true // required for search functionality on Kotlin and Android sites
    }

    fun loadUrl(url: String){
        webView.loadUrl(url)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
    }





}
