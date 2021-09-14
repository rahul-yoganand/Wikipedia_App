package com.example.wikipediaapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import com.example.wikipediaapp.R

class webView : AppCompatActivity() {
    lateinit var articleWeb:WebView
    lateinit var progressBar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        val title:String = intent.getStringExtra("title").toString()
        articleWeb = findViewById(R.id.wv_article)
        progressBar=findViewById(R.id.progess_bar)

        articleWeb.settings.setJavaScriptEnabled(true)
        progressBar.visibility=View.VISIBLE

        articleWeb.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url!!)
                return true
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                progressBar.visibility=View.GONE
            }
        }
        articleWeb.loadUrl("https://en.wikipedia.org/wiki/$title")
    }
    }