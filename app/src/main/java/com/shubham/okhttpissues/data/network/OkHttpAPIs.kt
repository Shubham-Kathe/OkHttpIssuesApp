package com.shubham.okhttpissues.data.network
import com.shubham.okhttpissues.data.network.response.OkHttpResponse
import com.shubham.okhttpissues.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface OkHttpAPIs {

    @GET("issues")
    suspend fun getIssueList(
    ): Response<List<OkHttpResponse>>


    companion object {
        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ): OkHttpAPIs {
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
            return Retrofit.Builder()
                .client(okHttpClient.build())
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(OkHttpAPIs::class.java)
        }
    }

}