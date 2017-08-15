package ru.wyeg.toothpickdemo.di;

import ru.wyeg.domain.GetUsersInteractor;
import toothpick.config.Module;

/**
 * @author Nikita Olifer.
 */
public class UserModule extends Module {

    public UserModule() {
        bind(GetUsersInteractor.class)
                .to(GetUsersInteractor.class)
                .singletonInScope();
    }
}

