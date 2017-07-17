package ru.wyeg.daggerdemo;

import java.util.concurrent.TimeUnit;

import io.reactivex.Scheduler;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import ru.wyeg.daggerdemo.mvp.SchedulerProvider;

/**
 * Created by onoli on 7/17/2017.
 */

public class TestSchedulersProvider implements SchedulerProvider {

    private final Scheduler immediate = new Scheduler() {
        @Override
        public Disposable scheduleDirect(@NonNull Runnable run, long delay, @NonNull TimeUnit unit) {
            // this prevents StackOverflowErrors when scheduling with a delay
            return super.scheduleDirect(run, 0, unit);
        }

        @Override
        public Worker createWorker() {
            return new ExecutorScheduler.ExecutorWorker(Runnable::run);
        }
    };

    @Override
    public Scheduler ui() {
        return immediate;
    }

    @Override
    public Scheduler io() {
        return immediate;
    }
}
