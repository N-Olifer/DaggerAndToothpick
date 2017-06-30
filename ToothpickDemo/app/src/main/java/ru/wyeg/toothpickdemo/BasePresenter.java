package ru.wyeg.toothpickdemo;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import toothpick.Scope;
import toothpick.Toothpick;

/**
 * @author Nikita Olifer.
 */
public abstract class BasePresenter<V extends MvpView> extends MvpBasePresenter<V> {

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    public void attachView(V view) {
        super.attachView(view);
        Scope scope = Toothpick.openScopes(App.getInstance(), this);
        Toothpick.inject(this, scope);
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        if (!retainInstance) {
            compositeDisposable.clear();
            Toothpick.closeScope(this);
        }
    }

    protected void addSubscription(Disposable subscription) {
        compositeDisposable.add(subscription);
    }
}
