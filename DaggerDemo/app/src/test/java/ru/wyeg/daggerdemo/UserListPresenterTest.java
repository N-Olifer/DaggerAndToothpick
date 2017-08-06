package ru.wyeg.daggerdemo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import ru.wyeg.daggerdemo.di.UsersSubcomponent;
import ru.wyeg.daggerdemo.mvp.SchedulerProvider;
import ru.wyeg.daggerdemo.users.UserListPresenter;
import ru.wyeg.daggerdemo.users.UserListView;
import ru.wyeg.data.UserEntity;
import ru.wyeg.domain.GetUsersInteractor;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by onoli on 7/17/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserListPresenterTest {

    @Spy SchedulerProvider schedulerProvider = new TestSchedulersProvider();
    @Mock GetUsersInteractor getUsersInteractor;
    @Mock UserListView view;

    @InjectMocks
    UserListPresenter presenter = new UserListPresenter(mock(UsersSubcomponent.class));

    @Before
    public void setUp() throws Exception {
        presenter.attachView(view);
    }

    @Test
    public void loadUsers() throws Exception {
        List<UserEntity> users = new ArrayList<>();
        when(getUsersInteractor.getUsers()).thenReturn(Single.just(users));

        presenter.loadUsers();

        verify(view).showUsers(users);
    }
}

