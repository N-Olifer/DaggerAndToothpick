package ru.wyeg.toothpickdemo;

import android.app.Application;

import org.greenrobot.greendao.database.Database;

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

        Scope appScope = Toothpick.openScope(this);

        appScope.installModules(new Module() {{
            bind(UserEntityDao.class).toInstance(daoSession.getUserEntityDao());
        }});
    }

    public static App getInstance() {
        return instance;
    }

    private void setubDB() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "users-db");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }
}
