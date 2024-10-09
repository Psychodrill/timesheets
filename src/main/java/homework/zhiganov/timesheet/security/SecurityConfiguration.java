package homework.zhiganov.timesheet.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfiguration {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
        // return http
        // .csrf(AbstractHttpConfigurer::disable)
        // .cors(AbstractHttpConfigurer::disable)
        // .authorizeHttpRequests(registry->{
        //                         registry.requestMatchers("/common/**").permitAll();
        //                         registry.requestMatchers("/admin/**").hasRole("ADMIN");
        //                         registry.requestMatchers("/user/**").hasRole("ADMIN","USER");
        //                         registry.anyRequest().authenticated();
    
        
        //                         })
        // .formLogin(httpSecurityFormLoginConfigurer ->{
        //     httpSecurityFormLoginConfigurer.successHandler(new AutenticationSuccessHandler())
        //     .permitAll();
        // })
        // .addFilterBefore(jwtAutenticationFilter,UsernamePasswordAuthenticationFilter.class )
        // .build();
        return http
                .authorizeHttpRequests(requests->requests
                                        .requestMatchers("/home/**").authenticated()
                                        .anyRequest().permitAll()
                                        )
                .formLogin(Customizer.withDefaults())
                .build();   
    }

}
