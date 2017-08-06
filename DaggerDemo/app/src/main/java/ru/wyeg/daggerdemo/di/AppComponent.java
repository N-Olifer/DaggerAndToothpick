package ru.wyeg.daggerdemo.di;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Nikita Olifer.
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    UsersSubcomponent plus(UsersModule userModule);
}

