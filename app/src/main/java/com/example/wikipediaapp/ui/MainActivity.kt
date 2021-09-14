package com.example.wikipediaapp.ui

import android.content.Context
import com.example.wikipediaapp.model.Pages
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wikipediaapp.MainViewModel
import com.example.wikipediaapp.MainViewModelFactory
import com.example.wikipediaapp.Repository
import com.example.wikipediaapp.data.WikiDao
import com.example.wikipediaapp.data.WikiDatabase
import com.example.wikipediaapp.data.WikiPage
import com.example.wikipediaapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), SearchItemClicked {
    lateinit var viewModel: MainViewModel
    lateinit var mWikiDao: WikiDao
    lateinit var mWikiDatabase: WikiDatabase
    lateinit var mRecyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mRecyclerView = binding.rvMain
        mWikiDatabase = WikiDatabase.getDatabase(this)
        mWikiDao = mWikiDatabase.wikiDao()
        val repository = Repository(this)
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.mQueryResults.observe(this, Observer { response ->
            binding.rvMain.adapter = RecyclerAdapter(response, this)
        })
        viewModel.isFound.observe(this, Observer { isFound ->
            if (!isFound) {
                Toast.makeText(this@MainActivity, "No result Found", Toast.LENGTH_SHORT).show()
            }

        })
        viewModel.mDBQueryResults.observe(this, Observer { response ->
            binding.rvMain.adapter = OfflineRecyclerAdapter(response)
        })

        val data = ArrayList<Pages>()

        // Setting the Adapter with the recyclerview
        binding.rvMain.layoutManager = LinearLayoutManager(this)
        binding.rvMain.adapter = RecyclerAdapter(data, this)

        binding.svMain.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText?.length != 0) {
                    if(checkForInternet(applicationContext))
                    viewModel.getQueryResult(newText.toString())
                    else
                        viewModel.getQueryFromDB(newText.toString())
                }
                return false
            }

        }

        )
    }

    override fun itemClicked(wikiPage: WikiPage) {
        viewModel.insertQuery(wikiPage)
        val intent = Intent(this, webView::class.java)
        intent.putExtra("title", "${wikiPage.title}")
        startActivity(intent)
    }

    private fun checkForInternet(context: Context): Boolean {

        // register activity with the connectivity manager service
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        // if the android version is equal to M
        // or greater we need to use the
        // NetworkCapabilities to check what type of
        // network has the internet connection
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            // Returns a Network object corresponding to
            // the currently active default data network.
            val network = connectivityManager.activeNetwork ?: return false

            // Representation of the capabilities of an active network.
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {
                // Indicates this network uses a Wi-Fi transport,
                // or WiFi has network connectivity
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true

                // Indicates this network uses a Cellular transport. or
                // Cellular has network connectivity
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

                // else return false
                else -> false
            }
        } else {
            // if the android version is below M
            @Suppress("DEPRECATION") val networkInfo =
                connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }
}