package ru.wyeg.toothpickdemo.user;

import javax.inject.Inject;

import ru.wyeg.domain.GetUsersInteractor;
import ru.wyeg.toothpickdemo.di.Scopes;
import ru.wyeg.toothpickdemo.mvp.BasePresenter;
import toothpick.Scope;
import toothpick.Toothpick;
import toothpick.config.Module;

/**
 * @author Nikita Olifer
 */
class UserPresenter extends BasePresenter<UserView> {

    @Override
    protected Scope openScope() {
        return Toothpick.openScopes(Scopes.APPLICATION, Scopes.USER, this);
    }

    @Inject
    GetUsersInteractor getUsersInteractor;

    public UserPresenter(Module... modules) {
        super(modules);
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
