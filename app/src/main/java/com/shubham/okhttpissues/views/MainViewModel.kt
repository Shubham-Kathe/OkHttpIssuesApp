package com.shubham.okhttpissues.views

import android.util.Log
import androidx.lifecycle.ViewModel
import com.shubham.okhttpissues.data.entity.ListOfIssues
import com.shubham.okhttpissues.data.repository.OkHttpIssuesRepository
import com.shubham.okhttpissues.utils.ApiException
import com.shubham.okhttpissues.utils.Coroutines
import com.shubham.okhttpissues.utils.NoInternetException
import com.shubham.okhttpissues.utils.ServerUnreachableException

class MainViewModel(
    private val okHttpIssuesRepository: OkHttpIssuesRepository
) : ViewModel() {
    private val TAG: String = MainViewModel::class.java.name
    var callBackListener: CallBackListener? = null


    fun getIssueListFromDb() = okHttpIssuesRepository.getIssuesListFromDb()


    fun fetchIssueList() {
        callBackListener?.onStarted()
        Coroutines.main {
            try {
                val response = okHttpIssuesRepository.getIssueListRepository()
                if (response.isEmpty()) {
                    callBackListener?.onFailed("Something went wrong")
                    return@main
                }
                val mList = ArrayList<ListOfIssues>()
                for (item in response){
                    val listOfIssues=ListOfIssues(
                        item.id!!,
                        item.title,
                        item.user?.login,
                        item.user?.avatarUrl,
                        item.milestone?.description,
                        item.updatedAt,
                    )
                    mList.add(listOfIssues)
                }
                okHttpIssuesRepository.insertOkHttpResponse(mList)
                callBackListener?.onSuccess()
            } catch (e: NoInternetException) {
                Log.e(TAG, "NoInternetException-", e)
                callBackListener?.onFailed(e.message!!)
            } catch (e: ServerUnreachableException) {
                Log.e(TAG, "ServerUnreachableException-", e)
                callBackListener?.onFailed(e.message!!)
            } catch (e: ApiException) {
                Log.e(TAG, "ApiException-", e)
                callBackListener?.onFailed(e.message!!)
            } catch (e: Exception) {
                Log.e(TAG, "Exception-", e)
                callBackListener?.onFailed("Something went wrong")
            }
        }
    }
}