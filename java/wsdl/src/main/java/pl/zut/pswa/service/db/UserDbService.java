package pl.zut.pswa.service.db;

import pl.zut.pswa.entity.User;

import java.util.List;

public interface UserDbService
        extends AbstractDbService<User> {

    List<User> findByFirstName(String firstName);

    List<User> findByLastName(String lastName);

    User findByPhone(String phone);

}
