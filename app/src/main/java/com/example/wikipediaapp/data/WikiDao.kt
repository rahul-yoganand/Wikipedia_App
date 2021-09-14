package com.example.wikipediaapp.data


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WikiDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPage(page: WikiPage)

    @Query("SELECT * FROM WikiPage where title like '%' || :titlename || '%'")
    fun getFromDb(titlename:String): Flow<List<WikiPage>>

}