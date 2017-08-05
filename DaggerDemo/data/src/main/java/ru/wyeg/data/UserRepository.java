package ru.wyeg.data;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * @author Nikita Olifer.
 */
public class UserRepository {

    private final UserEntityDao dao;

    @Inject
    public UserRepository(UserEntityDao dao) {
        this.dao = dao;
    }

    public Single<List<UserEntity>> getUsers() {
        return Single.fromCallable(() -> {
            List<UserEntity> users = dao.loadAll();
            if (users.isEmpty()) {
                dao.insert(new UserEntity(1, "Test1"));
                dao.insert(new UserEntity(2, "Test2"));
            }
            return dao.loadAll();
        });
    }

    public Single<UserEntity> getUser(long id) {
        return Single.fromCallable(() -> dao.load(id));
    }
}
