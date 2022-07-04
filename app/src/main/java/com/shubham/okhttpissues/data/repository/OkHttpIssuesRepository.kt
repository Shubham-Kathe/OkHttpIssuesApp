package com.shubham.okhttpissues.data.repository
import com.shubham.okhttpissues.data.db.AppDatabase
import com.shubham.okhttpissues.data.entity.ListOfIssues
import com.shubham.okhttpissues.data.network.OkHttpAPIs
import com.shubham.okhttpissues.data.network.response.OkHttpResponse
import com.shubham.okhttpissues.data.network.SafeApiRequest

class OkHttpIssuesRepository(
    private val appDatabase: AppDatabase,
    private val okHttpAPIs: OkHttpAPIs
) : SafeApiRequest() {

    suspend fun getIssueListRepository():List<OkHttpResponse>{
        return apiRequest {okHttpAPIs.getIssueList() }
    }

    suspend fun insertOkHttpResponse(listOfIssue: List<ListOfIssues>) =
        appDatabase.getOkHttpIssueListDeo().insertOkHttpResponse(listOfIssue)
    fun getIssuesListFromDb() = appDatabase.getOkHttpIssueListDeo().getIssuesList()

}