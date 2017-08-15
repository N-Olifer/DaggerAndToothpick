package ru.wyeg.toothpickdemo.mvp;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import ru.wyeg.toothpickdemo.di.Scopes;
import toothpick.Scope;
import toothpick.Toothpick;

/**
 * @author Nikita Olifer.
 */
public abstract class BasePresenter<V extends MvpView> extends MvpBasePresenter<V> {

    @Override
    public void attachView(V view) {
        super.attachView(view);
        if (!injected) {
            Scope scope = openScope();
            Toothpick.inject(this, scope);
            injected = true;
        }
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        if (!retainInstance) {
            closeScope();
        }
    }

    protected Scope openScope() {
        return Toothpick.openScopes(Scopes.APPLICATION, this);
    }

    protected void closeScope() {
        Toothpick.closeScope(this);
    }

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private boolean injected;

    @Inject
    protected SchedulerProvider schedulers;

    public BasePresenter() {
    }

    protected void addSubscription(Disposable subscription) {
        compositeDisposable.add(subscription);
    }
}
