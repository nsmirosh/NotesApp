package nick.mirosh.notes.data.di.modules

import android.util.Log
import dagger.Module
import dagger.Provides
import nick.mirosh.notes.data.di.annotations.MyCustomScope
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        Log.d("NetworkModule", "running Retrofit creation ")
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}