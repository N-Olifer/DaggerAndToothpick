package ru.wyeg.daggerdemo.di;

import dagger.Module;
import dagger.Provides;
import ru.wyeg.data.UserRepository;
import ru.wyeg.domain.GetUsersInteractor;

/**
 * @author Nikita Olifer
 */
@Module
public class UsersModule {

    @UsersScope
    @Provides
    public GetUsersInteractor provideGetUsersInteractor(UserRepository userRepository) {
        return new GetUsersInteractor(userRepository);
    }
}
