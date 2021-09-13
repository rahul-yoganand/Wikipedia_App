package com.example.wikipediaapp

import Pages
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(var repository: Repository) : ViewModel() {
    val mQueryResults = MutableLiveData<List<Pages>>()
    val isFound = MutableLiveData<Boolean>(true)

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
}
