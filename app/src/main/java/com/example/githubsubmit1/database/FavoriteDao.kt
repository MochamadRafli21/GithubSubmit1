package com.example.githubsubmit1.database

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(favorite: Favorite)

    @Update()
    fun update(favorite: Favorite)

    @Query("DELETE FROM favorite where login = :login")
    fun delete(login:String)

    @Query("SELECT * FROM favorite ORDER BY id ASC")
    fun getAllFavorites():LiveData<List<Favorite>>

    @Query("SELECT * FROM favorite WHERE login LIKE '%'||:query||'%'")
    fun getByLogin(query: String): LiveData<List<Favorite>>
}