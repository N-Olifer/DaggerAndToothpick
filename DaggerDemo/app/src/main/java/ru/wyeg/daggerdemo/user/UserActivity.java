package ru.wyeg.daggerdemo.user;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.TextView;

import com.hannesdorfmann.mosby3.mvp.MvpActivity;

import ru.wyeg.daggerdemo.App;
import ru.wyeg.daggerdemo.R;
import ru.wyeg.data.UserEntity;

/**
 * @author Nikita Olifer
 */
public class UserActivity extends MvpActivity<UserView, UserPresenter> implements UserView {

    private static final String EXTRA_USER_ID = "user_id";

    private TextView userNameView;

    public static Intent createIntent(Context context, long userId) {
        return new Intent(context, UserActivity.class)
                .putExtra(EXTRA_USER_ID, userId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        userNameView = (TextView) findViewById(R.id.user_name);

        getPresenter().loadUser(getIntent().getLongExtra(EXTRA_USER_ID, 0));
    }

    @NonNull
    @Override
    public UserPresenter createPresenter() {
        return new UserPresenter(App.getInstance().getUsersSubcomponent());
    }

    @Override
    public void setUser(UserEntity user) {
        userNameView.setText(user.getName());
    }
}
