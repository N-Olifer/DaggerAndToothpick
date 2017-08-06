package ru.wyeg.toothpickdemo;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import ru.wyeg.data.UserEntity;
import ru.wyeg.domain.GetUsersInteractor;
import ru.wyeg.toothpickdemo.mvp.SchedulerProvider;
import ru.wyeg.toothpickdemo.users.UserListPresenter;
import ru.wyeg.toothpickdemo.users.UserListView;
import toothpick.testing.ToothPickRule;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by onoli on 7/17/2017.
 */
public class UserListPresenterTest {

    @Mock SchedulerProvider schedulerProvider = new TestSchedulersProvider();
    @Mock UserListView view = mock(UserListView.class);
    @Mock GetUsersInteractor getUsersInteractor = mock(GetUsersInteractor.class);
    private final UserListPresenter presenter = new UserListPresenter();

    @Rule
    public ToothPickRule toothPickRule = new ToothPickRule(this, presenter);

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

