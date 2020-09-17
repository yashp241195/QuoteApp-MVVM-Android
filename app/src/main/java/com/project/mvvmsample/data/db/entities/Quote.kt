package com.project.mvvmsample.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Quote (
    @PrimaryKey(autoGenerate = false)
    val id: Int?,
    val quote: String?,
    val author:String?
)