package uz.nlg.mega.di

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.ramcosta.composedestinations.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.nlg.mega.data.local.SecurePrefs
import uz.nlg.mega.data.remote.ApiClient.server
import uz.nlg.mega.data.remote.ApiService
import uz.nlg.mega.data.remote.ApiServiceWithAuth
import uz.nlg.mega.utils.AccessToken
import java.net.SocketTimeoutException
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Suppress("NAME_SHADOWING")
@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context): Context {
        return context
    }

    // Server

    @Provides
    @Singleton
    fun getRetrofitClient(): Retrofit {
        return Retrofit
            .Builder()
            .client(buildClient())
            .baseUrl(server())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    private fun buildClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val builder = OkHttpClient.Builder()
        try {
            builder.callTimeout(1, TimeUnit.MINUTES)
                .addNetworkInterceptor(Interceptor { chain ->
                    var request = chain.request()
                    val builder = request.newBuilder()
                    builder.addHeader("Accept", "application/json")
                    request = builder.build()
                    chain.proceed(request)
                })
        } catch (e: Exception) {
            when (e) {
                is SocketTimeoutException -> {
                    throw SocketTimeoutException()
                }
            }
        }

        if (BuildConfig.DEBUG) {
            builder.addInterceptor(interceptor)
            builder.addNetworkInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }
        return builder.build()
    }

    @Provides
    @Singleton
    fun apiService(): ApiService {
        val newClient =
            OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS).addInterceptor(Interceptor { chain ->
                    val builder = chain.request().newBuilder()
                    builder.header("Content-Type", "application/json")
                    chain.proceed(builder.build())
                })
        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.apply { interceptor.level = HttpLoggingInterceptor.Level.BODY }
            newClient.addInterceptor(interceptor)
        }
        return getRetrofitClient().newBuilder().client(newClient.build()).build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun apiServiceWithAuth(pref: SecurePrefs): ApiServiceWithAuth {
        val newClient =
            OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS).addInterceptor(Interceptor { chain ->
                    val builder = chain.request().newBuilder()
                    builder.addHeader("Authorization", "Bearer " + pref.getString(AccessToken))
                    builder.header("Content-Type", "application/json")
                    chain.proceed(builder.build())
                })
        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.apply { interceptor.level = HttpLoggingInterceptor.Level.BODY }
            newClient.addInterceptor(interceptor)
        }
        return getRetrofitClient().newBuilder().client(newClient.build()).build()
            .create(ApiServiceWithAuth::class.java)
    }


    // Local Secure SharedPreference

    @Provides
    @Singleton
    fun securePref(context: Context): SharedPreferences {
        val masterKeys = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        return EncryptedSharedPreferences.create(
            "secured_prefs",
            masterKeys,
            provideContext(context),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

}