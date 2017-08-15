package ru.wyeg.toothpickdemo.users;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hannesdorfmann.mosby3.mvp.MvpActivity;

import java.util.List;

import ru.wyeg.data.UserEntity;
import ru.wyeg.toothpickdemo.R;
import ru.wyeg.toothpickdemo.user.UserActivity;

/**
 * @author Nikita Olifer.
 */
public class UserListActivity extends MvpActivity<UserListView, UserListPresenter> implements UserListView {

    private RecyclerView usersRecyclerView;
    private UsersAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        usersRecyclerView = (RecyclerView) findViewById(R.id.users_list);
        usersRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new UsersAdapter(user -> startActivity(UserActivity.createIntent(this, user.getId())));

        usersRecyclerView.setAdapter(adapter);

        getPresenter().loadUsers();
    }

    @NonNull
    @Override
    public UserListPresenter createPresenter() {
        return new UserListPresenter();
    }

    @Override
    public void showUsers(List<UserEntity> users) {
        adapter.setUsers(users);
    }
}
