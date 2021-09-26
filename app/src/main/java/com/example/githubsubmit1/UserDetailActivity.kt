package com.example.githubsubmit1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class UserDetailActivity : AppCompatActivity() {

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
        setContentView(R.layout.activity_user_detail)

        val data = intent.getParcelableExtra<Users>(MainActivity.INTENT_PARCELABLE)
        val tv_username: TextView = findViewById(R.id.user_name_detail)
        val tv_userfullname: TextView = findViewById(R.id.user_full_name_detail)
        val tv_company : TextView = findViewById(R.id.user_full_company_detail)
        val tv_city: TextView = findViewById(R.id.city_detail)
        val profilePic : ImageView = findViewById(R.id.user_profile_picture_detail)

        tv_username.text = data!!.Username
        tv_company.text = data.Company
        tv_userfullname.text = data.Name
        tv_city.text = data.City
        profilePic.setImageResource(data.profilePic)

        val sectionPagerAdapter = SectionPagerAdapter(this)
        val viewPager : ViewPager2 = findViewById(R.id.view_pager)
        viewPager.adapter = sectionPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabs, viewPager){tab,position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        supportActionBar?.elevation = 0f
    }
}