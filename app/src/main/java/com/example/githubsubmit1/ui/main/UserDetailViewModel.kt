package com.example.githubsubmit1.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.githubsubmit1.database.Favorite
import com.example.githubsubmit1.repository.FavoriteRepository



class UserDetailViewModel (application: Application): ViewModel() {
    private val mFavoriteRepository:FavoriteRepository = FavoriteRepository(application)

    fun insert(favorite: Favorite){
        mFavoriteRepository.insert(favorite)
    }

    fun update(favorite: Favorite){
        mFavoriteRepository.update(favorite)
    }

    fun delete(favorite: String){
        mFavoriteRepository.delete(favorite)
    }

    fun getByLogin(login:String):LiveData<List<Favorite>> {
        return mFavoriteRepository.getByLogin(login)
    }
}
