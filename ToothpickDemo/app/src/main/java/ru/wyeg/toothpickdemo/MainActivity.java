package ru.wyeg.toothpickdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ru.wyeg.toothpickdemo.di.Scopes;
import ru.wyeg.toothpickdemo.users.UserListActivity;
import toothpick.Toothpick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.main_show_users).setOnClickListener(v ->
                startActivity(new Intent(this, UserListActivity.class)));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toothpick.closeScope(Scopes.USER);
    }
}
