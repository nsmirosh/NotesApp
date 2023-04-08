package nick.mirosh.notes.data.di.modules

import dagger.Module
import nick.mirosh.notes.data.di.components.LoginComponent

// The "subcomponents" attribute in the @Module annotation tells Dagger what
// Subcomponents are children of the Component this module is included in.
@Module(subcomponents = [LoginComponent::class])
class SubcomponentsModule {}