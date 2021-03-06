package com.example.githubsubmit1

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubsubmit1.databinding.FragmentFollowingBinding
import com.example.githubsubmit1.ui.main.UserDetailActivity
import retrofit2.Call
import retrofit2.Response

class following : Fragment() {
    private lateinit var followingAdapter: FollowingAdapter
    private var _binding : FragmentFollowingBinding? = null
    private val binding get() =  _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        followingAdapter = FollowingAdapter(arrayListOf())
        showLoading(true)
        getFollowing()
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowingBinding.inflate(inflater,container,false)
        return binding!!.root
    }
    private fun showRecyclerCard(){
        _binding?.rvGithubUser?.layoutManager = LinearLayoutManager(this.context)
        _binding?.rvGithubUser?.adapter = followingAdapter


    }
    private fun getFollowing() {
        showLoading(true)
        val client = ApiConfig.getApiService().getUserFollowing((requireActivity() as UserDetailActivity).binding.userNameDetail.text.toString())
        client.enqueue(object : retrofit2.Callback<List<UserFollowingResponseItem>> {
            override fun onResponse(
                    call: Call<List<UserFollowingResponseItem>>,
                    response: Response<List<UserFollowingResponseItem>>
            ) {
                showLoading(false)
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        setFollowingData(responseBody)
                    }
                }
            }

            override fun onFailure(call: Call<List<UserFollowingResponseItem>>, t: Throwable) {
                showLoading(false)
                Log.e(ContentValues.TAG, "onFailure: ${t.message}")
            }


        })
    }

    private fun setFollowingData(items: List<UserFollowingResponseItem>) {
        followingAdapter.setFollowingData(items)
        showRecyclerCard()
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            _binding?.progressBar?.visibility = View.VISIBLE
        } else {
            _binding?.progressBar?.visibility = View.GONE
        }
    }
}
