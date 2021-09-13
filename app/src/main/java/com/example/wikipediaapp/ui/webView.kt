package com.example.wikipediaapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.wikipediaapp.R

class webView : AppCompatActivity() {
    lateinit var articleWeb:WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        val title:String = intent.getStringExtra("title").toString()
        articleWeb = findViewById(R.id.wv_article)
        articleWeb.settings.setJavaScriptEnabled(true)

        articleWeb.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url!!)
                return true
            }
        }
        articleWeb.loadUrl("https://en.wikipedia.org/wiki/$title")
    }
    }