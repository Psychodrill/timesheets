package homework.zhiganov.timesheet.repository;

import org.apache.el.stream.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import homework.zhiganov.timesheet.model.UserRole;

import java.util.*;

public interface UserRoleRepository extends JpaRepository<UserRole, Long>{
    
    
    //@Query(nativeQuery=true, value="select role_name from users_roles ur where ur.user_id=:userId")
    //List<String> findUserRolesByUserId(Long id);
    //List<String> findRoleNameByUserId(Long id);
    List<UserRole> findByUserId(Long id);

}
