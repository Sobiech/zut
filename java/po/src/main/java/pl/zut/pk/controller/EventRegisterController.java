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
import pl.zut.pk.domain.EventRegister;

public interface EventRegisterController {

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    Callable<EventRegister> getEventById(@PathVariable Long id)
        throws Exception;

    @DeleteMapping(value = "{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    void deleteEventById(@PathVariable Long id)
        throws Exception;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    Callable<EventRegister> createEvent(@RequestBody EventRegister eventRegister)
        throws Exception;

    @PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    Callable<EventRegister> updateEvent(@PathVariable Long id, @RequestBody EventRegister body)
        throws Exception;

}
