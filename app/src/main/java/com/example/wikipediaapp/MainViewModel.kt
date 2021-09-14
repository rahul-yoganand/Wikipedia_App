package com.example.wikipediaapp

import com.example.wikipediaapp.model.Pages
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wikipediaapp.data.WikiPage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(var repository: Repository) : ViewModel() {
    val mQueryResults = MutableLiveData<List<Pages>>()
    val mDBQueryResults = MutableLiveData<List<WikiPage>>()
    val isFound = MutableLiveData(true)

    fun getQueryResult(s: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getSearchResults(s)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val items = response.body()
                    if (items?.query != null) {
                        mQueryResults.value = items.query.pages

                        isFound.value = true
                    } else
                        isFound.value = false
                } else {
                    Log.e("RETROFIT_ERROR", response.code().toString())

                }
            }

        }
    }

    fun getQueryFromDB(s: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getSearchFromDb(s)
            withContext(Dispatchers.Main) {
                mDBQueryResults.value = response
            }
        }
    }


    fun insertQuery(page: WikiPage) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.InsertPage(page)

        }

    }
}
