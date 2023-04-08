package nick.mirosh.notes.data.di.modules;

import dagger.Binds;
import dagger.Module;
import nick.mirosh.notes.domain.repos.NoteRepository;
import nick.mirosh.notes.domain.repos.NoteRepositoryImpl;
import nick.mirosh.notes.presentation.theme.storage.NoteDao;
import nick.mirosh.notes.presentation.theme.storage.NoteDaoImpl;

@Module
public abstract class NoteDaoModule {
  @Binds
  abstract NoteDao bindNoteDaoImpl(NoteDaoImpl impl);

  @Binds
  abstract NoteRepository bindNoteRepositoryImpl(NoteRepositoryImpl impl);
}