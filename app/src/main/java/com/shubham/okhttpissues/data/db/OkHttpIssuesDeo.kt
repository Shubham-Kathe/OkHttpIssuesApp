package com.shubham.okhttpissues.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shubham.okhttpissues.data.entity.ListOfIssues


@Dao
interface OkHttpIssuesDeo {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOkHttpResponse(okHttpResponse: List<ListOfIssues>)

    @Query("SELECT * FROM ListOfIssues")
    fun getIssuesList(): LiveData<List<ListOfIssues>>
}