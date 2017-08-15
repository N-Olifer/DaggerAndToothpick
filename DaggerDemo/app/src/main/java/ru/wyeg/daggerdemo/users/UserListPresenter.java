package ru.wyeg.daggerdemo.users;

import javax.inject.Inject;

import ru.wyeg.daggerdemo.mvp.BasePresenter;
import ru.wyeg.daggerdemo.mvp.SchedulerProvider;
import ru.wyeg.domain.GetUsersInteractor;

/**
 * @author Nikita Olifer.
 */
public class UserListPresenter extends BasePresenter<UserListView> {

    private final GetUsersInteractor getUsersInteractor;

    @Inject
    public UserListPresenter(GetUsersInteractor getUsersInteractor, SchedulerProvider schedulerProvider) {
        super(schedulerProvider);
        this.getUsersInteractor = getUsersInteractor;
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
