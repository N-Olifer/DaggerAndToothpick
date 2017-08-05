package ru.wyeg.toothpickdemo.user;

import com.hannesdorfmann.mosby3.mvp.MvpView;

import ru.wyeg.data.UserEntity;

/**
 * @author Nikita Olifer
 */
public interface UserView extends MvpView {

    void setUser(UserEntity user);
}
