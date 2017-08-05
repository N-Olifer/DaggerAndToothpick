package ru.wyeg.toothpickdemo.user;

import javax.inject.Inject;

import ru.wyeg.domain.GetUsersInteractor;
import ru.wyeg.toothpickdemo.mvp.BasePresenter;

/**
 * @author Nikita Olifer
 */
public class UserPresenter extends BasePresenter<UserView> {

    @Inject
    GetUsersInteractor getUsersInteractor;

    void loadUser(long id) {
        addSubscription(getUsersInteractor.getUser(id)
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.ui())
                .subscribe(user -> getView().setUser(user),
                        error -> {

                        }));
    }
}
