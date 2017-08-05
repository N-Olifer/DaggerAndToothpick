package ru.wyeg.daggerdemo.di;

import dagger.Subcomponent;
import ru.wyeg.daggerdemo.user.UserPresenter;
import ru.wyeg.daggerdemo.users.UserListPresenter;

/**
 * @author Nikita Olifer
 */
@UsersScope
@Subcomponent(
        modules = {
                UsersModule.class
        }
)
public interface UsersSubcomponent {

    void inject(UserPresenter userPresenter);

    void inject(UserListPresenter userListPresenter);
}