package com.shubham.okhttpissues.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.shubham.okhttpissues.data.entity.ListOfIssues

@Database(
    entities = [ListOfIssues::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getOkHttpIssueListDeo(): OkHttpIssuesDeo

    companion object {
        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any() // only single instance of database

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "OkHttpIssues.db"
            ).build()
    }

}