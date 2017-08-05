package ru.wyeg.daggerdemo.users;

import javax.inject.Inject;

import ru.wyeg.daggerdemo.di.UsersSubcomponent;
import ru.wyeg.daggerdemo.mvp.BasePresenter;
import ru.wyeg.domain.GetUsersInteractor;

/**
 * @author Nikita Olifer.
 */
public class UserListPresenter extends BasePresenter<UserListView> {

    @Inject
    GetUsersInteractor getUsersInteractor;

    public UserListPresenter(UsersSubcomponent usersSubcomponent) {
        usersSubcomponent.inject(this);
    }

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
