package ru.wyeg.toothpickdemo;

import android.app.Application;

import org.greenrobot.greendao.database.Database;

import ru.wyeg.data.DaoMaster;
import ru.wyeg.data.DaoSession;
import ru.wyeg.toothpickdemo.di.AppModule;
import ru.wyeg.toothpickdemo.di.Scopes;
import toothpick.Scope;
import toothpick.Toothpick;

/**
 * @author Nikita Olifer.
 */
public class App extends Application {

    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        // TODO use this code for optimization
//        Toothpick.setConfiguration(Configuration.forProduction().disableReflection());
//        MemberInjectorRegistryLocator.setRootRegistry(new com.example.smoothie.MemberInjectorRegistry());
//        FactoryRegistryLocator.setRootRegistry(new com.example.smoothie.FactoryRegistry());

        setubDB();
        setupDI();
    }

    private void setubDB() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "users-db");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    private void setupDI() {
        Scope appScope = Toothpick.openScope(Scopes.APPLICATION);
        appScope.installModules(new AppModule(daoSession));
    }
}
