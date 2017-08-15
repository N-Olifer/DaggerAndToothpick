package ru.wyeg.daggerdemo.user;

import javax.inject.Inject;

import ru.wyeg.daggerdemo.mvp.BasePresenter;
import ru.wyeg.daggerdemo.mvp.SchedulerProvider;
import ru.wyeg.domain.GetUsersInteractor;

/**
 * @author Nikita Olifer
 */
public class UserPresenter extends BasePresenter<UserView> {

    private final GetUsersInteractor getUsersInteractor;

    @Inject
    public UserPresenter(GetUsersInteractor getUsersInteractor, SchedulerProvider schedulerProvider) {
        super(schedulerProvider);
        this.getUsersInteractor = getUsersInteractor;
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
