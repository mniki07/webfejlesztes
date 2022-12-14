package hu.unideb.inf.core.user;

import hu.unideb.inf.core.user.persistence.entity.MyUser;
import hu.unideb.inf.core.user.persistence.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Optional<MyUser> getUserByUsername(String username) {
        Optional<MyUser> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return Optional.of(user.get());
        }
        return Optional.empty();
    }

    @Override
    public void registerUser(String username, String password) {
        Optional<MyUser> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            throw new UserAlreadyExistsException("A user already exists with this username!");
        }
        userRepository.save(new MyUser(
                username,
                bCryptPasswordEncoder.encode(password),
                MyUser.Role.USER
        ));
    }

}
