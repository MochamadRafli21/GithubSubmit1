package com.example.githubsubmit1.ui.main

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.CompoundButton
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubsubmit1.*
import com.example.githubsubmit1.databinding.ActivityMainBinding
import com.google.android.material.switchmaterial.SwitchMaterial
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var listUsersAdapter: ListUsersAdapter
    private lateinit var binding : ActivityMainBinding
    private val Context.dataStore:DataStore<Preferences> by preferencesDataStore(name = "setting")

    companion object {
        const val TAG = "MainActivity"
        private var USERNAME = "username"
        const val INTENT_PARCELABLE = "OBJECT INTENT"
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

        val switchTheme = menu?.findItem(R.id.menu2)?.actionView as SwitchMaterial
        val pref = SettingPreferences.getInstance(dataStore)
        val mainViewModel = ViewModelProvider(this, ViewModelFactory(pref)).get(
            MainViewModel::class.java
        )

        mainViewModel.getThemeSettings().observe(this,
            {isDarkMode:Boolean ->
                if(isDarkMode){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    switchTheme.isChecked = true
                }else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    switchTheme.isChecked = false
                }
            }
        )

        switchTheme.setOnCheckedChangeListener{_:CompoundButton, isChecked: Boolean ->
            mainViewModel.saveThemeSetting(isChecked)
        }

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = binding.search

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
        showLoading(true)
        val client = ApiConfig.getApiService().getUsers(USERNAME)
        client.enqueue(object : retrofit2.Callback<GithubApiUserResponse> {
            override fun onResponse(
                call: Call<GithubApiUserResponse>,
                response: Response<GithubApiUserResponse>
            ) {
                showLoading(false)
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null && responseBody.totalCount != 0) {
                        setGithubUserData(responseBody.items)
                    }else{
                        showLoading(false)
                        Toast.makeText(applicationContext, "Cant find user data", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<GithubApiUserResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
                Toast.makeText(applicationContext, "Cant find user data", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun setGithubUserData(items: List<ItemsItem>) {
        listUsersAdapter.setUserData(items)

    }

    private fun showRecyclerCard(){
        binding.rvGithubUser.layoutManager = LinearLayoutManager(this)
        binding.rvGithubUser.adapter = listUsersAdapter

        listUsersAdapter.setOnItemClickCallback(object : ListUsersAdapter.OnItemClickCallback {
            override fun onItemClicked(data: ItemsItem) {
                val intent = Intent(this@MainActivity, UserDetailActivity::class.java)
                intent.putExtra(INTENT_PARCELABLE, data.login)
                startActivity(intent)
            }

        })
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

}