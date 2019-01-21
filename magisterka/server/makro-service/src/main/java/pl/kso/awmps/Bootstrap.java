package pl.kso.awmps;

import java.util.stream.Stream;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import pl.kso.awmps.domain.repository.CarRepository;
import pl.kso.awmps.domain.entity.Car;

@SpringBootApplication
@EnableJpaRepositories
public class Bootstrap {

    public static void main(String[] args) {
        SpringApplication.run(Bootstrap.class, args);
    }

    @Bean
    ApplicationRunner init(CarRepository repository) {
        return args -> {
            Stream.of("Ferrari",
                      "Jaguar",
                      "Porsche",
                      "Lamborghini",
                      "Bugatti",
                      "AMC Gremlin",
                      "Triumph Stag",
                      "Ford Pinto",
                      "Yugo GV").forEach(name -> {
                Car car = new Car();
                car.setName(name);
                repository.save(car);
            });
            repository.findAll().forEach(System.out::println);
        };
    }

}
