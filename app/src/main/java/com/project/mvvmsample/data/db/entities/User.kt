package com.project.mvvmsample.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

// we want to store only one user,
// autoGenerate = false
const val CURRENT_USER_ID = 0

@Entity
data class User (
    var id : Int? = null,
    var name : String? = null,
    var email : String? = null,
    var token : String? = null
){
    @PrimaryKey(autoGenerate = false)
    var uid : Int = CURRENT_USER_ID
}