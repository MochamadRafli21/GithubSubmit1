package com.example.githubsubmit1.helper

import androidx.recyclerview.widget.DiffUtil
import com.example.githubsubmit1.database.Favorite

class FavoriteDiffCallBack(private val mOldFavoriteList: List<Favorite>,private val mNewFavoriteList: List<Favorite>): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldFavoriteList.size
    }

    override fun getNewListSize(): Int {
        return mNewFavoriteList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
       return mOldFavoriteList[oldItemPosition].id == mNewFavoriteList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = mOldFavoriteList[oldItemPosition]
        val newItem = mNewFavoriteList[newItemPosition]

        return oldItem.login == newItem.login  && oldItem.name == newItem.name && oldItem.AvatarUrl == newItem.AvatarUrl && newItem.location == oldItem.location && newItem.company == oldItem.company
    }
}