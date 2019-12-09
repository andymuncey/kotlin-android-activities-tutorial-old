package com.tinyappco.kotlinhelp

import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), MainFragment.RequestListener {

    private val WEB_BROWSE_REQUEST = 0

    override fun onUserRequest(url: String) {
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            (webFragment as WebFragment).loadUrl(url)
        } else {
            val intent = Intent(this, WebSearchActivity::class.java)
            intent.putExtra("url", url)
            startActivityForResult(intent, WEB_BROWSE_REQUEST)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            val url = data?.getStringExtra("url")
            (fragment as MainFragment).updatePreviousUrl(url)
        }
    }

}
