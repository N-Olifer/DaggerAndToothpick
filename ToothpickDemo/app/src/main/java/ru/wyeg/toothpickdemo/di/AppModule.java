package ru.wyeg.toothpickdemo.di;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.wyeg.data.DaoSession;
import ru.wyeg.data.UserEntityDao;
import ru.wyeg.toothpickdemo.mvp.SchedulerProvider;
import toothpick.config.Module;

/**
 * Created by onoli on 7/17/2017.
 */

public class AppModule extends Module {

    public AppModule(DaoSession daoSession) {
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
    }
}
