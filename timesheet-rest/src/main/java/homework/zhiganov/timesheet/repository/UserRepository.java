package homework.zhiganov.timesheet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import homework.zhiganov.timesheet.model.User;
import java.util.*;

public interface UserRepository extends JpaRepository<User, Long>{

    Optional<User> findByLogin(String login);
}
