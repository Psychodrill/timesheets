package homework.zhiganov.timesheet.repository;

import java.util.List;

import org.springframework.data.repository.*;


@NoRepositoryBean
public interface ReadOnlyRepository<T,ID> extends Repository<T, ID> {

    List<T> findAllById(ID id);
    List<T> findAll();
}
