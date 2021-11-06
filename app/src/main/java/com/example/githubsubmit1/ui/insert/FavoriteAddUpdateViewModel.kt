package com.example.githubsubmit1.ui.insert

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.githubsubmit1.database.Favorite
import com.example.githubsubmit1.repository.FavoriteRepository

class FavoriteAddUpdateViewModel (application: Application): ViewModel(){
    private val mFavoriteRepository: FavoriteRepository = FavoriteRepository(application)

    fun getAllFavorites(): LiveData<List<Favorite>> = mFavoriteRepository.getAllFavorites()
}
