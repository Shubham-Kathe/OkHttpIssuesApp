package com.shubham.okhttpissues.base

import android.app.Application
import com.shubham.okhttpissues.data.db.AppDatabase
import com.shubham.okhttpissues.data.network.NetworkConnectionInterceptor
import com.shubham.okhttpissues.data.network.OkHttpAPIs
import com.shubham.okhttpissues.data.repository.OkHttpIssuesRepository
import com.shubham.okhttpissues.views.MainViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

class OkHttpIssuesApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@OkHttpIssuesApplication))
        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { OkHttpAPIs(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { OkHttpIssuesRepository(instance(), instance()) }
        bind() from singleton { MainViewModelFactory(instance()) }

    }
}