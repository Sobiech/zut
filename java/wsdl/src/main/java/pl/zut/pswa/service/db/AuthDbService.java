package pl.zut.pswa.service.db;

import pl.zut.pswa.entity.User;
import pl.zut.pswa.enums.Role;

public interface AuthDbService extends AbstractDbService<User> {

    User findByEmailPasswordAndRole(String email, String password, Role role);

}
