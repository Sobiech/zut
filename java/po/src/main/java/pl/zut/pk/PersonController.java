package pl.zut.pk;

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

public interface PersonController {

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    Callable<Person> getPersonById(@PathVariable("id") Long personId)
        throws Exception;

    @DeleteMapping(value = "{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    void deletePersonById(@PathVariable("id") Long personId)
        throws Exception;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    Callable<Person> createPerson(@RequestBody Person body)
        throws Exception;

    @PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    Callable<Person> updatePerson(@PathVariable("id") Long personId, @RequestBody Person body)
        throws Exception;

}
