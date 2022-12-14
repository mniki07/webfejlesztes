package hu.unideb.inf.core.user.persistence.repository;

import hu.unideb.inf.core.user.persistence.entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<MyUser, Integer> {

    Optional<MyUser> findByUsername(String username);

}
