package com.code.designpatternapp.data.module;

import com.code.designpatternapp.App;
import com.code.designpatternapp.ui.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                AppModule.class,
                DaoModule.class,
                ServiceModule.class,
                MapperModule.class,
                RepositoryModule.class

        }
)
public interface AppComponent {
    void inject(App app);
    void inject(MainActivity mainActivity);
}
