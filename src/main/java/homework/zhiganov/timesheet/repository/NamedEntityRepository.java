package homework.zhiganov.timesheet.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

//
@NoRepositoryBean
public interface NamedEntityRepository<T,ID> extends JpaRepository<T,ID>{
    Optional<List<T>> findByName(String name);
}
