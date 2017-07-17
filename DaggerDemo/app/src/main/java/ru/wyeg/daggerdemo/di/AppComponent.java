package ru.wyeg.daggerdemo.di;

import dagger.Component;
import ru.wyeg.daggerdemo.users.UsersPresenter;

/**
 * @author Nikita Olifer.
 */
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(UsersPresenter usersPresenter);
}
