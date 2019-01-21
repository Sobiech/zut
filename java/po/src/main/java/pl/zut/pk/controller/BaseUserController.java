package pl.zut.pk.controller;

import java.util.concurrent.Callable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.zut.pk.domain.User;
import pl.zut.pk.domain.repository.UserRepository;

@Slf4j
@RestController
@RequestMapping(path = "/user")
public class BaseUserController implements UserController {

    private final UserRepository repository;

    @Autowired
    public BaseUserController(UserRepository userRepository) {
        this.repository = userRepository;
    }

    @Override
    public Callable<User> getUserById(Long id) {
        return () -> repository.findById(id).orElseThrow(() -> new Exception("Could not found person"));
    }

    @Override
    public void deleteUserById(Long id) {
        repository.deleteById(id);
        log.info("Deleted person by id: {}", id);
    }

    @Override
    public Callable<User> createUser(User body) {
        return () -> {
            User user = repository.save(body);
            log.info("Created user {}", user);
            return user;
        };
    }

    @Override
    public Callable<User> updateUser(Long id, User body) {
        return () -> updatePersonByEntity(id, body);
    }

    private User updatePersonByEntity(Long id, User user)
        throws Exception {

        User updatedUser = repository.findById(id).orElseThrow(() -> new Exception("Could not found user"));

        log.info("Got user to update: {}", updatedUser);
        updatedUser.setEmail(user.getEmail());
        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setLastName(user.getLastName());
        return repository.saveAndFlush(updatedUser);
    }

}
