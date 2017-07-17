package ru.wyeg.daggerdemo.users;

import javax.inject.Inject;

import ru.wyeg.daggerdemo.di.AppComponent;
import ru.wyeg.daggerdemo.mvp.BasePresenter;
import ru.wyeg.domain.GetUsersInteractor;

/**
 * @author Nikita Olifer.
 */
public class UsersPresenter extends BasePresenter<UsersView> {

    @Inject
    GetUsersInteractor getUsersInteractor;

    public UsersPresenter(AppComponent appComponent) {
        appComponent.inject(this);
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
