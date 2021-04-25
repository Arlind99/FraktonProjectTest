package com.example.recyclerview

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ServiceGenerator {

        companion object {
            var BASE_API_URL:String = "https://reqres.in/api/"
            fun <S> call(serviceClass: Class<S>): S {

                val logging = HttpLoggingInterceptor()

                // set  desired log level
                logging.level = HttpLoggingInterceptor.Level.BODY

                val httpClient = OkHttpClient.Builder()

                httpClient.readTimeout(120, TimeUnit.SECONDS)
                httpClient.connectTimeout(5, TimeUnit.SECONDS)

                //get username and password from SAP
                    /*val credentials = "shaxhija:sitfsw1ss"
                    val basic = "Basic " + Base64.encodeToString(credentials.toByteArray(), Base64.NO_WRAP)*/


                    httpClient.addInterceptor { chain ->
                        val original = chain.request()

                        val originalHttpUrl = original.url()

                        //Add default parameters
                        val url = originalHttpUrl.newBuilder()
                            .build()

                        val requestBuilder = original.newBuilder()
                            //.header("Authorization", basic)
                            .header("Accept", "application/json").url(url)
                            .method(original.method(), original.body())

                        val request = requestBuilder.build()
                        chain.proceed(request)
                    }

                    // add logging as last interceptor
                    httpClient.addInterceptor(logging)


                val client = httpClient.build()


                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()

                return retrofit.create(serviceClass)
            }
        }
}