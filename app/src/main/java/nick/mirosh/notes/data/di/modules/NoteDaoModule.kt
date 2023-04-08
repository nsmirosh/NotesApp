package nick.mirosh.notes.data.di.modules

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ActivityRetainedComponent
import nick.mirosh.notes.domain.repos.NoteRepository
import nick.mirosh.notes.domain.repos.NoteRepositoryImpl
import nick.mirosh.notes.presentation.theme.storage.NoteDao
import nick.mirosh.notes.presentation.theme.storage.NoteDaoImpl

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class NoteDaoModule {
    @Binds
    abstract fun bindNoteDaoImpl(impl: NoteDaoImpl?): NoteDao?
    @Binds
    abstract fun bindNoteRepositoryImpl(impl: NoteRepositoryImpl?): NoteRepository?
}