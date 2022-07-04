package com.shubham.okhttpissues.views

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shubham.okhttpissues.data.repository.OkHttpIssuesRepository

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(
    private val okHttpIssuesRepository: OkHttpIssuesRepository

): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(okHttpIssuesRepository) as T
    }


}