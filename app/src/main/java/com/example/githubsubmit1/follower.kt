package com.example.githubsubmit1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubsubmit1.databinding.FragmentFollowerBinding
import retrofit2.Call
import retrofit2.Response


class follower : Fragment() {
    private lateinit var followerAdapter: FollowerAdapter
    private var _binding : FragmentFollowerBinding? = null
    private val binding get() =  _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        followerAdapter = FollowerAdapter(arrayListOf())
        getFollower()
        showRecyclerCard()
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowerBinding.inflate(inflater,container,false)
        return binding!!.root
    }
    private fun showRecyclerCard(){
        _binding?.rvGithubUser?.layoutManager = LinearLayoutManager(this.context)
        _binding!!.rvGithubUser.adapter = followerAdapter


    }
    private fun getFollower() {
        val client = ApiConfig.getApiService().getUserFollower("hendisantika")
        client.enqueue(object : retrofit2.Callback<UserFollowerResponse> {
            override fun onResponse(
                    call: Call<UserFollowerResponse>,
                    response: Response<UserFollowerResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {

                        setFollowerData(responseBody.userFollowerResponse as List<UserFollowerResponseItem>)
                    }
                }
            }

            override fun onFailure(call: Call<UserFollowerResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }


        })
    }

    private fun setFollowerData(items: List<UserFollowerResponseItem>) {
        followerAdapter.setFollowerData(items)
    }

}