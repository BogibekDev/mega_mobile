package uz.nlg.mega.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.nlg.mega.data.remote.ApiClient.server
import uz.nlg.mega.data.remote.ApiService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    // Server

    @Provides
    @Singleton
    fun getRetrofitClient(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(server())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun apiService(): ApiService = getRetrofitClient().create(ApiService::class.java)

}