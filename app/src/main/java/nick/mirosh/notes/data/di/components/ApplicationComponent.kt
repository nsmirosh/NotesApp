package nick.mirosh.notes.data.di.components

import dagger.Component
import nick.mirosh.notes.data.di.modules.NetworkModule
import nick.mirosh.notes.data.di.modules.NoteDaoModule
import nick.mirosh.notes.data.di.modules.SubcomponentsModule
import nick.mirosh.notes.presentation.main.MainActivity
import nick.mirosh.notes.presentation.new_note.NewNoteActivity
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
        SubcomponentsModule::class,
        NoteDaoModule::class,
        ViewModelModule::class,
    ]
)
interface ApplicationComponent {

    fun inject(activity: MainActivity)

    fun inject(activity: NewNoteActivity)

    // This function exposes the LoginComponent Factory out of the graph so consumers
    // can use it to obtain new instances of LoginComponent
    fun loginComponent(): LoginComponent.Factory
}