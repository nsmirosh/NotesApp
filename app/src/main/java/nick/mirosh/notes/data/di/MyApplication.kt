package nick.mirosh.notes.data.di

import android.app.Application
import nick.mirosh.notes.data.di.components.ApplicationComponent

// appComponent lives in the Application class to share its lifecycle
class MyApplication: Application() {
    // Reference to the application graph that is used across the whole app
    val appComponent: ApplicationComponent = DaggerApplicationComponent.create()
}