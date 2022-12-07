package com.tinyappco.kotlinhelp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tinyappco.kotlinhelp.databinding.ActivityMainBinding
import com.tinyappco.kotlinhelp.databinding.FragmentMainBinding
import java.net.URLEncoder

class MainFragment : Fragment() {

    private lateinit var binding : FragmentMainBinding

    interface MainFragmentRequestListener {
        fun onUserRequest(url: String)
    }

    private var requestListener : MainFragmentRequestListener? = null

    private var prevUrl : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainFragmentRequestListener){
            requestListener = context
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

    private fun loadWebActivity(url: String) {
        requestListener?.onUserRequest(url)
    }


    fun updatePreviousUrl(url: String?){
        if (url != null) {
            prevUrl = url
            binding.btnPrevious.visibility = View.VISIBLE
        } else {
            binding.btnPrevious.visibility = View.INVISIBLE
        }
    }

    private val encodedSearchTerm : String
        get() = URLEncoder.encode(binding.etSearch.text.toString(),"UTF-8")

    private fun toggleButtonsState(enabled: Boolean){
        binding.btnAndroid.isEnabled = enabled
        binding.btnKotlin.isEnabled = enabled
        binding.btnStackOverflow.isEnabled = enabled
        binding.btnGoogle.isEnabled = enabled
    }

}