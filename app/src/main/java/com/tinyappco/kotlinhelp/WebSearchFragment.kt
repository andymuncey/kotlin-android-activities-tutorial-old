package com.tinyappco.kotlinhelp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import com.tinyappco.kotlinhelp.databinding.FragmentWebSearchBinding


class WebSearchFragment : Fragment() {

    private lateinit var binding : FragmentWebSearchBinding
    private var url: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentWebSearchBinding.inflate(inflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.webView.webViewClient = WebViewClient() //prevents opening in browser app
        binding.webView.settings.javaScriptEnabled = true // required for search functionality on Kotlin and Android sites

        if (url != null){
            binding.webView.loadUrl(url!!)
        }

    }

    fun loadUrl(url:String){

        if (this::binding.isInitialized){
            //do something with binding
        }

        if (this::binding.isInitialized){
            binding.webView.loadUrl(url)
        }
        this.url = url
    }

    fun getCurrentUrl() : String?{
        return binding.webView.url
    }


}