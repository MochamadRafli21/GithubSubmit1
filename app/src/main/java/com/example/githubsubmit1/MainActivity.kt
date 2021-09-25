package com.example.githubsubmit1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvUsers: RecyclerView
    private var list: ArrayList<Users> = arrayListOf()

    companion object{
        val INTENT_PARCELABLE = "OBJECT INTENT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        rvUsers= findViewById(R.id.rv_github_user)
        rvUsers.setHasFixedSize(true)

        list.addAll(UsersData().listData)
        showRecyclerCard()
    }

    private fun showRecyclerCard(){
        rvUsers.layoutManager = LinearLayoutManager(this)
        val listUserAdapter = ListUsersAdapter(list)
        rvUsers.adapter = listUserAdapter

        listUserAdapter.setOnItemClickCallback(object : ListUsersAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Users) {
                val intent = Intent(this@MainActivity, UserDetailActivity::class.java)
                intent.putExtra(INTENT_PARCELABLE, data)
                startActivity(intent)
            }

        })
    }

}