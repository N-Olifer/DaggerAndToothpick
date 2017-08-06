package ru.wyeg.toothpickdemo.users;

import javax.inject.Inject;

import ru.wyeg.domain.GetUsersInteractor;
import ru.wyeg.toothpickdemo.di.Scopes;
import ru.wyeg.toothpickdemo.mvp.BasePresenter;
import toothpick.Scope;
import toothpick.Toothpick;
import toothpick.config.Module;

/**
 * @author Nikita Olifer.
 */
public class UserListPresenter extends BasePresenter<UserListView> {

    @Inject
    GetUsersInteractor getUsersInteractor;

    public UserListPresenter(Module... modules) {
        super(modules);
    }

    @Override
    protected Scope openScope() {
        return Toothpick.openScopes(Scopes.APPLICATION, Scopes.USER, this);
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
