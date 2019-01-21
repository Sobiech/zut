package pl.zut.pk;

import java.util.concurrent.Callable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(path = "/person")
public class BasePersonController implements PersonController {

    private final PersonRepository repository;

    @Autowired
    public BasePersonController(PersonRepository personRepository) {
        this.repository = personRepository;
    }

    @Override
    public Callable<Person> getPersonById(Long personId) {
        return () -> repository.findById(personId).orElseThrow(() -> new Exception("Could not found person"));
    }

    @Override
    public void deletePersonById(Long personId) {
        repository.deleteById(personId);
        log.info("Deleted person by id: {}", personId);
    }

    @Override
    public Callable<Person> createPerson(Person body) {
        return () -> {
            Person person = repository.save(body);
            log.info("Created person {}", person);
            return person;
        };
    }

    @Override
    public Callable<Person> updatePerson(Long personId, Person body) {
        return () -> updatePersonByEntity(personId, body);
    }

    private Person updatePersonByEntity(Long personId, Person person)
        throws Exception {

        Person updatedPerson = repository.findById(personId).orElseThrow(() -> new Exception("Could not found person"));

        log.info("Got person to update: {}", updatedPerson);
        updatedPerson.setFirstName(person.getFirstName());
        updatedPerson.setLastName(person.getLastName());
        updatedPerson.setOld(person.getOld());
        return repository.saveAndFlush(updatedPerson);
    }

}
