package homework.zhiganov.timesheet.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import homework.zhiganov.timesheet.model.User;
import homework.zhiganov.timesheet.repository.UserRepository;
import homework.zhiganov.timesheet.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import java.util.*;

@Component
@RequiredArgsConstructor
public class MyCustomUserDetailsService implements UserDetailsService{

    @Autowired
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(username)
            .orElseThrow(()-> new UsernameNotFoundException("User not found"));
        
        List<SimpleGrantedAuthority> userRoles=userRoleRepository.findByUserId(user.getId()).stream().map(it->new SimpleGrantedAuthority(it.getRoleName())).toList();
        //List<SimpleGrantedAuthority> roles= userRoles.stream().map(SimpleGrantedAuthority::new).toList();
        
        //todo 
        //return new org.springframework.security.core.userdetails.User(user.getLogin(),user.getPassword(),roles);
        //return null;
        return new org.springframework.security.core.userdetails.User(user.getLogin(),user.getPassword(), userRoles);
    }

}
