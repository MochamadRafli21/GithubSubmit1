package com.example.githubsubmit1.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.githubsubmit1.database.Favorite
import com.example.githubsubmit1.database.FavoriteDao
import com.example.githubsubmit1.database.FavoriteRoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Flow
import kotlin.collections.List as List

class FavoriteRepository(application: Application) {
    private val mFavoriteDao: FavoriteDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = FavoriteRoomDatabase.getDatabase(application)
        mFavoriteDao = db.favoriteDao()
    }

    fun getAllFavorites(): LiveData<List<Favorite>> = mFavoriteDao.getAllFavorites()

    fun insert(favorite: Favorite){
        executorService.execute{mFavoriteDao.insert(favorite)}
    }

    fun delete(favorite: String){
        executorService.execute{mFavoriteDao.delete(favorite)}
    }

    fun update(favorite: Favorite){
        executorService.execute{mFavoriteDao.update(favorite)}
    }

    fun getByLogin(login:String):LiveData<List<Favorite>> {
        return mFavoriteDao.getByLogin(login)
    }

}