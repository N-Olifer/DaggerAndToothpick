package ru.wyeg.toothpickdemo;

import android.app.Application;

import org.greenrobot.greendao.database.Database;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.wyeg.data.DaoMaster;
import ru.wyeg.data.DaoSession;
import ru.wyeg.data.UserEntityDao;
import toothpick.Scope;
import toothpick.Toothpick;
import toothpick.config.Module;

/**
 * @author Nikita Olifer.
 */
public class App extends Application {

    private DaoSession daoSession;

    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();

//        Toothpick.setConfiguration(Configuration.forProduction().disableReflection());
//        MemberInjectorRegistryLocator.setRootRegistry(new com.example.smoothie.MemberInjectorRegistry());
//        FactoryRegistryLocator.setRootRegistry(new com.example.smoothie.FactoryRegistry());

        instance = this;

        setubDB();

        Scope appScope = Toothpick.openScope(Scopes.APPLICATION);

        appScope.installModules(new Module() {{
            bind(UserEntityDao.class).toInstance(daoSession.getUserEntityDao());
            bind(SchedulerProvider.class).toInstance(new SchedulerProvider() {
                @Override
                public Scheduler ui() {
                    return AndroidSchedulers.mainThread();
                }

                @Override
                public Scheduler io() {
                    return Schedulers.io();
                }
            });
        }});
    }

    private void setubDB() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "users-db");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }
}
