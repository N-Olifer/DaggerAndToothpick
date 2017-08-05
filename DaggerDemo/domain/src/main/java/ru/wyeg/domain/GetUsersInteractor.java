package ru.wyeg.domain;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import ru.wyeg.data.UserEntity;
import ru.wyeg.data.UserRepository;

/**
 * @author Nikita Olifer.
 */
public class GetUsersInteractor {

    private final UserRepository userRepository;

    @Inject
    public GetUsersInteractor(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Single<List<UserEntity>> getUsers() {
        return userRepository.getUsers();
    }

    public Single<UserEntity> getUser(long id) {
        return userRepository.getUser(id);
    }
}
