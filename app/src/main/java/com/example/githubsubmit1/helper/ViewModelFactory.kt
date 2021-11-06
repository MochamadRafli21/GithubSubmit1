package com.example.githubsubmit1.helper

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githubsubmit1.ui.main.MainViewModel
import com.example.githubsubmit1.ui.main.UserDetailViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory private constructor(private val mApplication:Application):ViewModelProvider.NewInstanceFactory(){

    companion object{
        @Volatile
        private var INSTANCE : ViewModelFactory? = null

        @JvmStatic
        fun getInstance(application: Application):ViewModelFactory{
            if (INSTANCE == null){
                synchronized(ViewModelFactory::class.java){
                    INSTANCE = ViewModelFactory(application)
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }

    override fun <T: ViewModel?> create(modelClass: Class<T>):T{
        if(modelClass.isAssignableFrom(UserDetailViewModel::class.java)){
            return UserDetailViewModel(mApplication) as T
        }
        throw IllegalArgumentException("Unknown view model class: ${modelClass.name}")
    }
}