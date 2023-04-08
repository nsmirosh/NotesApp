package nick.mirosh.notes.data.di.annotations;


import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import dagger.MapKey;

@Documented
@Target({java.lang.annotation.ElementType.METHOD})
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@MapKey
public @interface ViewModelKey {

    Class<? extends androidx.lifecycle.ViewModel> value();
}

