package ru.wyeg.daggerdemo;

import android.app.Application;

import org.greenrobot.greendao.database.Database;

import ru.wyeg.daggerdemo.di.AppComponent;
import ru.wyeg.daggerdemo.di.AppModule;
import ru.wyeg.daggerdemo.di.DaggerAppComponent;
import ru.wyeg.daggerdemo.di.UsersModule;
import ru.wyeg.daggerdemo.di.UsersSubcomponent;
import ru.wyeg.data.DaoMaster;
import ru.wyeg.data.DaoSession;

/**
 * @author Nikita Olifer.
 */
public class App extends Application {

    private DaoSession daoSession;

    private AppComponent appComponent;
    private UsersSubcomponent usersSubcomponent;

    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        setubDB();
        setupDI();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public UsersSubcomponent getUsersSubcomponent() {
        return usersSubcomponent;
    }

    public static App getInstance() {
        return instance;
    }

    private void setupDI() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(daoSession))
                .build();

        usersSubcomponent = appComponent.plus(new UsersModule());
    }

    private void setubDB() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "users-db");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }
}
