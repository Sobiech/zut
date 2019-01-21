package pl.zut.pk.controller;

import java.util.concurrent.Callable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.zut.pk.domain.User;

public interface UserController {

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    Callable<User> getUserById(@PathVariable Long id)
        throws Exception;

    @DeleteMapping(value = "{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    void deleteUserById(@PathVariable Long id)
        throws Exception;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    Callable<User> createUser(@RequestBody User body)
        throws Exception;

    @PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    Callable<User> updateUser(@PathVariable Long id, @RequestBody User body)
        throws Exception;

}
