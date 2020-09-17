package com.project.mvvmsample.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.project.mvvmsample.data.db.entities.Quote
import com.project.mvvmsample.data.db.entities.User

@Database(
    entities = [
        User :: class,
        Quote :: class
    ],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun getUserDao() : UserDao
    abstract fun getQuoteDao() : QuoteDao

    companion object{

        @Volatile
        private var instance :AppDatabase?=null
        private val LOCK = Any()

        private val databaseName = "Mydatabase.db"

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance?:buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase (context: Context)=
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                databaseName
            ).build()

    }

}