package ru.wyeg.data;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author Nikita Olifer.
 */
@Entity
public class UserEntity {

    @Id
    private long id;

    private String name;

    @Generated(hash = 1611124801)
    public UserEntity(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Generated(hash = 1433178141)
    public UserEntity() {
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
