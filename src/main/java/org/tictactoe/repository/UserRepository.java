package org.tictactoe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tictactoe.entity.User;

import java.util.Collection;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Collection<Object> findByEmailAndIdNot(String email, Long id);
}
