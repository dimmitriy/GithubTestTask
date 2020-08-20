package com.github.test.di

import android.annotation.SuppressLint
import com.github.test.Const
import com.github.test.api.DateTimeTypeConverter
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.joda.time.DateTime
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
abstract class NetworkModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        @Singleton
        internal fun provideGson(): Gson {
            return GsonBuilder()
                .registerTypeAdapter(DateTime::class.java, DateTimeTypeConverter())
                .create()
        }

        @JvmStatic
        @Provides
        @Singleton
        internal fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory {
            return GsonConverterFactory.create(gson)
        }

        @JvmStatic
        @Provides
        @Singleton
        internal fun provideRxJava2ConverterFactory(): RxJava2CallAdapterFactory {
            return RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())
        }

        @SuppressLint("CheckResult")
        @JvmStatic
        @Provides
        @Singleton
        internal fun initOkBuilder(): OkHttpClient.Builder {
            val builder = OkHttpClient.Builder()
            builder.connectTimeout(Const.CONNECTION_TIMEOUT.toLong(), TimeUnit.SECONDS)
            builder.readTimeout(Const.READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
            builder.writeTimeout(Const.WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)

            val logging = HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)

            builder.addInterceptor(logging)
            return builder
        }

        @JvmStatic
        @Provides
        @Singleton
        internal fun initRetrofitBuilder(rxAdapter: RxJava2CallAdapterFactory, gson: Gson): Retrofit.Builder {
            val builder = Retrofit.Builder()
            return builder
                .baseUrl(Const.API_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(rxAdapter)
        }

        @JvmStatic
        @Provides
        @Singleton
        internal fun getRetrofit(okBuilder: OkHttpClient.Builder, retrofitBuilder: Retrofit.Builder): Retrofit {
            return retrofitBuilder
                .client(okBuilder.build())
                .build()
        }

    }

}