package by.itstep.shop.service;

import by.itstep.shop.dao.model.User;

public interface UserService {

    User findByUsername(String name);

    User save(User user);

    void updateUser(User userFromDb, User user);

    void addMoney(Double money, User user);
}
