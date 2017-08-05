package ru.wyeg.daggerdemo.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.wyeg.daggerdemo.mvp.SchedulerProvider;
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

    @Singleton
    @Provides
    public UserEntityDao provideUserEntityDao() {
        return daoSession.getUserEntityDao();
    }

    @Singleton
    @Provides
    public SchedulerProvider provideSchedulerProvider() {
        return new SchedulerProvider() {
            @Override
            public Scheduler ui() {
                return AndroidSchedulers.mainThread();
            }

            @Override
            public Scheduler io() {
                return Schedulers.io();
            }
        };
    }
}
