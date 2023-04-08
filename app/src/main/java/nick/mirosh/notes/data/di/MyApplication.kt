package nick.mirosh.notes.data.di

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

// appComponent lives in the Application class to share its lifecycle

@HiltAndroidApp
class MyApplication: Application() {
    // Reference to the application graph that is used across the whole app
}