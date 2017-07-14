package ru.wyeg.daggerdemo.di;

import dagger.Module;
import dagger.Provides;
import ru.wyeg.data.DaoSession;
import ru.wyeg.data.UserEntityDao;

/**
 * @author Nikita Olifer.
 */
@Module
public class AppModule {


    private final DaoSession daoSession;

    public AppModule(DaoSession daoSession) {
        this.daoSession = daoSession;
    }

    @Provides
    public UserEntityDao provideUserEntityDao() {
        return daoSession.getUserEntityDao();
    }
}
