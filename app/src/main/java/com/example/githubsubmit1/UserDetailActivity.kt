package com.example.githubsubmit1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class UserDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_user_detail)

        val data = intent.getParcelableExtra<Users>(MainActivity.INTENT_PARCELABLE)

        val tv_username: TextView = findViewById(R.id.user_name_detail)
        val tv_userfullname: TextView = findViewById(R.id.user_full_name_detail)
        val tv_company : TextView = findViewById(R.id.user_full_company_detail)
        val tv_following: TextView = findViewById(R.id.following_detail)
        val tv_repository: TextView = findViewById(R.id.detail_user_repostory)
        val tv_city: TextView = findViewById(R.id.city_detail)
        val tv_follower : TextView = findViewById(R.id.user_follower_detail)
        val profilePic : ImageView = findViewById(R.id.user_profile_picture_detail)
        val follower = "follower: ${data?.Follower.toString()}"
        val repository = "repo: ${data?.Repository.toString()}"
        val following = "following: ${data?.Following.toString()}"

        tv_username.text = data!!.Username
        tv_company.text = data.Company
        tv_userfullname.text = data.Name
        tv_city.text = data.City
        tv_follower.text = follower
        tv_following.text = following
        tv_repository.text = repository
        profilePic.setImageResource(data.profilePic)
    }
}