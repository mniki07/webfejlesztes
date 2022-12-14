package hu.unideb.inf.security;

import hu.unideb.inf.core.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeRequests(authConfig -> {
                    authConfig.antMatchers("/").permitAll();
                    authConfig.antMatchers("/index").permitAll();
                    authConfig.antMatchers("/register").permitAll();
                    authConfig.antMatchers("/makeRegistration").permitAll();
                    authConfig.antMatchers("/login").permitAll();
                    authConfig.antMatchers("/logout").permitAll();
                    authConfig.anyRequest().authenticated();
                })
                .formLogin(Customizer.withDefaults())
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService(@Autowired UserService userService) {
        return new MyDatabaseUserDetailsService(userService);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
