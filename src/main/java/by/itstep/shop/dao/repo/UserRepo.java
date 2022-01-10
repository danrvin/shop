package by.itstep.shop.dao.repo;

import by.itstep.shop.dao.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User>findByEmail(String email);
    //User findByEmail(String email);
    User findByUsername(String username);
    User findUserById(Long id);
    List<User> findAll();
}
