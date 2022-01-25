package by.itstep.shop.service;


import by.itstep.shop.dao.model.User;
import java.util.List;
import java.util.Optional;

public interface UserService {

    User loadUserByUsername(String username);

    User findByUserId(Long id);

    User findByUsername(String name);

    Optional<User> findByUsernameOptional(String name);

    User findByEmail(String email);

    User save(User user);

    void deleteUser(User user);

    void setStartMoney(User user);

    void userSetMoneyFromItem(User user, Long id);

    List<User> findAll();

    User getOne(Long id);

    List<User> users();

    User updateUser(User userFromDb, User user);
}
