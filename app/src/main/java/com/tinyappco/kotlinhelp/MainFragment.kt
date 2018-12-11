package com.tinyappco.kotlinhelp

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_main.*
import java.net.URLEncoder


class MainFragment : Fragment() {

    interface RequestListener {
        fun onUserRequest(url: String)
    }

    private var requestListener: RequestListener? = null

    private var prevUrl: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is RequestListener) {
            requestListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        requestListener = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btnGoogle.setOnClickListener {
            loadWebActivity("https://google.co.uk/search?q=$encodedSearchTerm")
        }

        btnStackOverflow.setOnClickListener {
            loadWebActivity("https://stackoverflow.com/search?q=$encodedSearchTerm")
        }

        btnKotlin.setOnClickListener {
            loadWebActivity("https://kotlinlang.org/?q=$encodedSearchTerm&p=0")
        }

        btnAndroid.setOnClickListener {
            loadWebActivity("https://developer.android.com/s/results/?q=$encodedSearchTerm")
        }

        btnPrevious.setOnClickListener {
            loadWebActivity(prevUrl ?: "")
        }

        etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                toggleButtonsState(etSearch.text.length > 1)
            }
        })

        toggleButtonsState(false)
    }

    private val encodedSearchTerm: String
        get() = URLEncoder.encode(etSearch.text.toString(), "UTF-8")


    private fun loadWebActivity(url: String) {
        requestListener?.onUserRequest(url)
    }

    fun updatePreviousUrl(url: String?) {
        if (url != null) {
            prevUrl = url
            btnPrevious.visibility = View.VISIBLE
        } else {
            btnPrevious.visibility = View.INVISIBLE
        }
    }

    private fun toggleButtonsState(enabled: Boolean) {
        btnAndroid.isEnabled = enabled
        btnKotlin.isEnabled = enabled
        btnStackOverflow.isEnabled = enabled
        btnGoogle.isEnabled = enabled
    }

}
