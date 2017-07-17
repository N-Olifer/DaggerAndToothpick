package ru.wyeg.toothpickdemo.users;

import javax.inject.Inject;

import ru.wyeg.domain.GetUsersInteractor;
import ru.wyeg.toothpickdemo.mvp.BasePresenter;

/**
 * @author Nikita Olifer.
 */
public class UsersPresenter extends BasePresenter<UsersView> {

    @Inject
    GetUsersInteractor getUsersInteractor;

    public void loadUsers() {
        addSubscription(getUsersInteractor.getUsers()
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.ui())
                .subscribe(users -> {
                    if (isViewAttached()) {
                        getView().showUsers(users);
                    }
                }, error -> {

                }));
    }
}
