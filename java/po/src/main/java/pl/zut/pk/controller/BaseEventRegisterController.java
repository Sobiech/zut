package pl.zut.pk.controller;

import java.util.concurrent.Callable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.zut.pk.domain.EventRegister;
import pl.zut.pk.domain.User;
import pl.zut.pk.domain.repository.EventRegisterRepository;
import pl.zut.pk.domain.repository.UserRepository;

@Slf4j
@RestController
@RequestMapping(path = "/event")
public class BaseEventRegisterController implements EventRegisterController {

    private final EventRegisterRepository repository;
    private final UserRepository userRepository;

    @Autowired
    public BaseEventRegisterController(EventRegisterRepository eventRegisterRepository, UserRepository userRepository) {
        this.repository = eventRegisterRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Callable<EventRegister> getEventById(Long id) {
        return () -> repository.findById(id).map(event -> {
            event.setUserId(event.getUser().getId());
            return event;
        }).orElseThrow(() -> new Exception("Could not " + "found " + "person"));
    }

    @Override
    public void deleteEventById(Long id) {
        repository.deleteById(id);
        log.info("Deleted event by id: {}", id);
    }

    @Override
    public Callable<EventRegister> createEvent(EventRegister body) {
        return () -> {

            User user = userRepository.findById(body.getUserId()).orElseThrow(() -> new Exception("No user with given id"));
            body.setUser(user);
            EventRegister eventRegister = repository.save(body);
            log.info("Created EventRegister {}", eventRegister);
            return eventRegister;
        };
    }

    @Override
    public Callable<EventRegister> updateEvent(Long id, EventRegister body) {
        return () -> updateEventByEntity(id, body);
    }

    private EventRegister updateEventByEntity(Long id, EventRegister event)
        throws Exception {

        EventRegister updatedEventRegister = repository.findById(id)
                                                       .orElseThrow(() -> new Exception("Could not found EventRegister"));

        log.info("Got EventRegister to update: {}", updatedEventRegister);
        updatedEventRegister.setEventType(event.getEventType());
        updatedEventRegister.setUser(event.getUser());
        return repository.saveAndFlush(updatedEventRegister);
    }
}
