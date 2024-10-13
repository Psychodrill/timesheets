package homework.zhiganov.timesheet.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import homework.zhiganov.timesheet.model.Role;

@Configuration
public class SecurityConfiguration {

    // @Bean
    // GrantedAuthorityDefaults grantedAuthorityDefaults(){
    //     return new GrantedAuthorityDefaults("MY_ROLE_PREFIX");
    // } 
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{

        return http
                .authorizeHttpRequests(requests->requests
               
                .requestMatchers("/home/projects/**").hasAuthority("ADMIN")
                //.requestMatchers("/home/projects/**").hasRole(Role.ADMIN.getName()) //MY_ROLE_PREFIX
                .requestMatchers("/home/timesheet/**").hasAnyAuthority("USER","ADMIN")
                .requestMatchers("/timesheets").hasAnyAuthority("REST","ADMIN")
                .requestMatchers("/projects").hasAnyAuthority("REST","ADMIN")
                //.anyRequest().authenticated()
                                        )
                .formLogin(Customizer.withDefaults())
                .build();   
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
