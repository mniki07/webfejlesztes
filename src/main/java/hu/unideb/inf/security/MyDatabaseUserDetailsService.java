package hu.unideb.inf.security;

import hu.unideb.inf.core.user.UserService;
import hu.unideb.inf.core.user.persistence.entity.MyUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

@AllArgsConstructor
public class MyDatabaseUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MyUser> user = userService.getUserByUsername(username);
        if (user.isPresent()) {
            return User.builder()
                    .username(username)
                    .password(user.get().getPassword())
                    .authorities(user.get().getRole().toString())
                    .build();
        }
        throw new UsernameNotFoundException("No such username!");
    }

}
