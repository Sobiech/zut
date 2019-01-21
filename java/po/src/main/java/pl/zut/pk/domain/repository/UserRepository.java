package pl.zut.pk.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.zut.pk.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
