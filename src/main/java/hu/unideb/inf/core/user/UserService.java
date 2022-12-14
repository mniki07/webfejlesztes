package hu.unideb.inf.core.user;

import hu.unideb.inf.core.user.persistence.entity.MyUser;

import java.util.Optional;

public interface UserService {

    Optional<MyUser> getUserByUsername(String username);

    void registerUser(String username, String password);

}
