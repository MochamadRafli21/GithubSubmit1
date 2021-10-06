package com.example.githubsubmit1

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubsubmit1.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var listUsersAdapter: ListUsersAdapter
    private lateinit var binding : ActivityMainBinding

    companion object {
        private const val TAG = "MainActivity"
        private var USERNAME = "username"
        val INTENT_PARCELABLE = "OBJECT INTENT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        listUsersAdapter = ListUsersAdapter(arrayListOf())
        setContentView(binding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu!!.findItem(R.id.search).actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                USERNAME = query.toString()
                findUser()
                showRecyclerCard()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        return true
    }

    private fun findUser() {
        val client = ApiConfig.getApiService().getUsers(USERNAME)
        client.enqueue(object : retrofit2.Callback<GithubApiUserResponse> {
            override fun onResponse(
                call: Call<GithubApiUserResponse>,
                response: Response<GithubApiUserResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        setGithubUserData(responseBody.items)
                    }
                }
            }

            override fun onFailure(call: Call<GithubApiUserResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }

    private fun setGithubUserData(items: List<ItemsItem>) {
        listUsersAdapter.setUserData(items)
    }

    private fun showRecyclerCard(){
        binding.rvGithubUser.layoutManager = LinearLayoutManager(this)
        binding.rvGithubUser.adapter = listUsersAdapter

        listUsersAdapter.setOnItemClickCallback(object : ListUsersAdapter.OnItemClickCallback{
            override fun onItemClicked(data: ItemsItem) {
                val intent = Intent(this@MainActivity, UserDetailActivity::class.java)
                intent.putExtra(INTENT_PARCELABLE, data.login)
                startActivity(intent)
            }

        })
    }

}