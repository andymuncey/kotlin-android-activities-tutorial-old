package com.tinyappco.kotlinhelp

import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.tinyappco.kotlinhelp.databinding.ActivityMainBinding
import java.net.URLEncoder

class MainActivity : AppCompatActivity(), MainFragment.MainFragmentRequestListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }


    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result ->

        if (result.resultCode == Activity.RESULT_OK) {
            val url = result.data?.getStringExtra("url")

            val fragment = supportFragmentManager.findFragmentById(R.id.mainFragmentContainerView) as MainFragment

            fragment.updatePreviousUrl(url)
        }
    }

    private fun loadWebActivity(url: String) {

        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
            val fragment = supportFragmentManager.findFragmentById(R.id.landWebFragmentContainerView) as WebSearchFragment
            fragment.loadUrl(url)
        } else {
            val intent = Intent(this, WebSearchActivity::class.java)
            intent.putExtra("url", url)
            resultLauncher.launch(intent)
        }
    }

    override fun onUserRequest(url: String) {
        loadWebActivity(url)
    }


}
