package com.example.githubsubmit1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.githubsubmit1.databinding.ActivityMainBinding
import com.example.githubsubmit1.databinding.ActivityUserDetailBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import retrofit2.Call
import retrofit2.Response

class UserDetailActivity() : AppCompatActivity(R.layout.activity_user_detail){
    private lateinit var userDetailData: UserDetailResponse
    private lateinit var binding: ActivityUserDetailBinding
    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.follower,
            R.string.following
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        getUserDetail()
        binding = ActivityUserDetailBinding.inflate(layoutInflater)
        userDetailData = UserDetailResponse()
        setContentView(binding.root)

        supportActionBar?.elevation = 0f
    }

    private fun getUserDetail(){
        val username = intent.getStringExtra(MainActivity.INTENT_PARCELABLE)
        val client = ApiConfig.getApiService().getUserDetail(username.toString())
        client.enqueue(object : retrofit2.Callback<UserDetailResponse>{
            override fun onResponse(call: Call<UserDetailResponse>, response: Response<UserDetailResponse>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        setData(responseBody)
                    }
                }
            }
            override fun onFailure(call: Call<UserDetailResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }


        })
    }

    private fun setData(responseBody: UserDetailResponse) {
        userDetailData = responseBody
        binding.userNameDetail.text = userDetailData!!.login
        binding.userFullCompanyDetail.text = userDetailData.company
        binding.userFullNameDetail.text = userDetailData.name
        binding.cityDetail.text = userDetailData.location
        Glide.with(binding.root)
                .load(userDetailData.avatarUrl)
                .apply(RequestOptions().override(250, 250))
                .into(binding.userProfilePictureDetail)

        val sectionPagerAdapter = SectionPagerAdapter(this)
        val viewPager : ViewPager2 = findViewById(R.id.view_pager)
        viewPager.adapter = sectionPagerAdapter
        val tabs: TabLayout = binding.tabs
        TabLayoutMediator(tabs, viewPager){tab,position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

    }
}