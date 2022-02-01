package by.itstep.shop.service;


import by.itstep.shop.dao.model.User;
import java.util.List;
import java.util.Optional;

public interface UserService {

    User loadUserByUsername(String username);

    User findByUserId(Long id);

    User findByUsername(String name);

    Optional<User> findByUsernameOptional(String name);

    User save(User user);

    void deleteUser(User user);

    void setStartMoney(User user);

    List<User> findAll();

    List<User> users();

    User updateUser(User userFromDb, User user);

    User addMoney(Double money, User user);
}
