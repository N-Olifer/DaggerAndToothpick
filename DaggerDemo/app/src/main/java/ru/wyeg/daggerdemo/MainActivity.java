package ru.wyeg.daggerdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ru.wyeg.daggerdemo.users.UserListActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.main_show_users).setOnClickListener(v -> startActivity(new Intent(this, UserListActivity.class)));
    }
}
