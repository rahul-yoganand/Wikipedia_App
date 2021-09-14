package com.example.wikipediaapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class WikiPage (
    @PrimaryKey var pageid:Int,
    @ColumnInfo var title:String,
    @ColumnInfo var description:String,
    //@ColumnInfo var thumbnail:String,
    )