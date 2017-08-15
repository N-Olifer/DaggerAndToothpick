package ru.wyeg.toothpickdemo.users;

import javax.inject.Inject;

import ru.wyeg.domain.GetUsersInteractor;
import ru.wyeg.toothpickdemo.di.Scopes;
import ru.wyeg.toothpickdemo.di.UserModule;
import ru.wyeg.toothpickdemo.mvp.BasePresenter;
import toothpick.Scope;
import toothpick.Toothpick;

/**
 * @author Nikita Olifer.
 */
public class UserListPresenter extends BasePresenter<UserListView> {

    @Inject
    GetUsersInteractor getUsersInteractor;

    @Override
    protected Scope openScope() {
        Toothpick.openScopes(Scopes.APPLICATION, Scopes.USER)
                .installModules(new UserModule());

        return Toothpick.openScopes(Scopes.APPLICATION, Scopes.USER, this);
    }

    @Override
    protected void closeScope() {
        Toothpick.closeScope(Scopes.USER);
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
