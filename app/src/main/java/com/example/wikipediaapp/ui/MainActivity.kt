package com.example.wikipediaapp.ui

import com.example.wikipediaapp.model.Pages
import android.content.Intent
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
    lateinit var mWikiDao :WikiDao
    lateinit var mWikiDatabase: WikiDatabase
    lateinit var mRecyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mRecyclerView=binding.rvMain
        val mWikiDatabase = WikiDatabase.getDatabase(this)
        mWikiDao=mWikiDatabase.wikiDao()
        val repository = Repository(this)
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.mQueryResults.observe(this, Observer { response ->
            binding.rvMain.adapter = RecyclerAdapter(response,this)
        })
        viewModel.isFound.observe(this, Observer { isFound ->
            if (!isFound) {
                Toast.makeText(this@MainActivity, "No result Found", Toast.LENGTH_SHORT).show()
            }

        })

        val data = ArrayList<Pages>()

        // Setting the Adapter with the recyclerview
        binding.rvMain.layoutManager = LinearLayoutManager(this)
        binding.rvMain.adapter = RecyclerAdapter(data,this)

        binding.svMain.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText?.length != 0) {
                    viewModel.getQueryResult(newText.toString())
                }
                return false
            }

        }

        )
    }

    override fun itemClicked(wikiPage: WikiPage) {
        viewModel.insertQuery(wikiPage)
        val intent= Intent(this,webView::class.java)
        intent.putExtra("title","${wikiPage.title}")
        startActivity(intent)
    }
}