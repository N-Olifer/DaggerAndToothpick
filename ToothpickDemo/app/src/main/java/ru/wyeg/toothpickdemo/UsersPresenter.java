package ru.wyeg.toothpickdemo;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.wyeg.domain.GetUsersInteractor;

/**
 * @author Nikita Olifer.
 */
public class UsersPresenter extends BasePresenter<UsersView> {

    @Inject
    GetUsersInteractor getUsersInteractor;

    public void loadUsers() {
        addSubscription(getUsersInteractor.getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(users -> {
                    if (isViewAttached()) {
                        getView().showUsers(users);
                    }
                }, error -> {

                }));
    }
}
