package ru.wyeg.daggerdemo.user;

import javax.inject.Inject;

import ru.wyeg.daggerdemo.di.UsersSubcomponent;
import ru.wyeg.daggerdemo.mvp.BasePresenter;
import ru.wyeg.domain.GetUsersInteractor;

/**
 * @author Nikita Olifer
 */
public class UserPresenter extends BasePresenter<UserView> {

    @Inject
    GetUsersInteractor getUsersInteractor;

    UserPresenter(UsersSubcomponent usersSubcomponent) {
        usersSubcomponent.inject(this);
    }

    void loadUser(long id) {
        addSubscription(getUsersInteractor.getUser(id)
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
            .subscribe(user -> getView().setUser(user),
                    error -> {

            }));
    }
}
