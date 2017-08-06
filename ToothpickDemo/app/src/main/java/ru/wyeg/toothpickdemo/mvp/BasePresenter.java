package ru.wyeg.toothpickdemo.mvp;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import ru.wyeg.toothpickdemo.di.Scopes;
import toothpick.Scope;
import toothpick.Toothpick;
import toothpick.config.Module;

/**
 * @author Nikita Olifer.
 */
public abstract class BasePresenter<V extends MvpView> extends MvpBasePresenter<V> {

    @Override
    public void attachView(V view) {
        super.attachView(view);
        if (!injected) {
            Scope scope = openScope();
            scope.installModules(modules);
            Toothpick.inject(this, scope);
            injected = true;
        }
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        if (!retainInstance) {
            compositeDisposable.clear();
            Toothpick.closeScope(this);
        }
    }

    protected Scope openScope() {
        return Toothpick.openScopes(Scopes.APPLICATION, this);
    }

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private boolean injected;
    private Module[] modules;

    @Inject
    protected SchedulerProvider schedulers;

    public BasePresenter(Module... modules) {
        this.modules = modules;
    }

    protected void addSubscription(Disposable subscription) {
        compositeDisposable.add(subscription);
    }
}
