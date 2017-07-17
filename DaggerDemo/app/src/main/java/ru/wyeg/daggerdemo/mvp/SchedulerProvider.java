package ru.wyeg.daggerdemo.mvp;

import io.reactivex.Scheduler;

/**
 * Created by onoli on 7/17/2017.
 */
public interface SchedulerProvider {
    Scheduler ui();
    Scheduler io();
}
