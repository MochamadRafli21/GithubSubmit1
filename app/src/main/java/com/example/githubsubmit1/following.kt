package com.example.githubsubmit1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubsubmit1.databinding.FragmentFollowingBinding
import retrofit2.Call
import retrofit2.Response

class following : Fragment() {
    private lateinit var listUsersAdapter: FollowingAdapter
    private var _binding : FragmentFollowingBinding? = null
    private val binding get() =  _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listUsersAdapter = FollowingAdapter(arrayListOf())
        getFollowing()
        showRecyclerCard()
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowingBinding.inflate(inflater,container,false)
        return binding!!.root
    }
    private fun showRecyclerCard(){
        _binding!!.rvGithubUser.layoutManager = LinearLayoutManager(this.context)
        _binding!!.rvGithubUser.adapter = listUsersAdapter


    }
    private fun getFollowing() {
        val client = ApiConfig.getApiService().getUserFollowing("hendisantika")
        client.enqueue(object : retrofit2.Callback<UserFollowingResponse> {
            override fun onResponse(
                    call: Call<UserFollowingResponse>,
                    response: Response<UserFollowingResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        setFollowingData(responseBody.userFollowingResponse as List<UserFollowingResponseItem>)
                    }
                }
            }

            override fun onFailure(call: Call<UserFollowingResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }


        })
    }

    private fun setFollowingData(items: List<UserFollowingResponseItem>) {
        listUsersAdapter.setFollowingData(items)
    }
}
