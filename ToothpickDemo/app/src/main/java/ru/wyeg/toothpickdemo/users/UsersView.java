package ru.wyeg.toothpickdemo.users;

import com.hannesdorfmann.mosby3.mvp.MvpView;

import java.util.List;

import ru.wyeg.data.UserEntity;

/**
 * @author Nikita Olifer.
 */
public interface UsersView extends MvpView {

    void showUsers(List<UserEntity> users);
}
