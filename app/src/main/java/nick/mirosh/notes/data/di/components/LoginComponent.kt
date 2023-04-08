package nick.mirosh.notes.data.di.components

import dagger.Subcomponent
import nick.mirosh.notes.presentation.login.LoginActivity

@Subcomponent
interface LoginComponent {

    // This tells Dagger that LoginActivity requests injection from LoginComponent
    // so that this subcomponent graph needs to satisfy all the dependencies of the
    // fields that LoginActivity is injecting


    // Factory that is used to create instances of this subcomponent
    @Subcomponent.Factory
    interface Factory {
        fun create(): LoginComponent
    }


    fun inject(loginActivity: LoginActivity)
}