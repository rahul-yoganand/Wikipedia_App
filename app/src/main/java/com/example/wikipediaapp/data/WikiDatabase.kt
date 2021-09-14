package com.example.wikipediaapp.data

import com.example.wikipediaapp.model.Pages
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [WikiPage::class], version = 1, exportSchema = false)
abstract class WikiDatabase : RoomDatabase() {
    abstract fun wikiDao(): WikiDao
    companion object {
        private lateinit var INSTANCE: WikiDatabase
        fun getDatabase(context: Context): WikiDatabase {
            synchronized(WikiDatabase::class.java) {
                if (!::INSTANCE.isInitialized) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        WikiDatabase::class.java,
                        "database"
                    ).build()
                }
            }

            return INSTANCE
        }
    }
}