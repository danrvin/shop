package by.itstep.shop.service;


import by.itstep.shop.dao.model.User;
import java.util.List;

public interface UserService {

    User loadUserByUsername(String username);

    User findByUserId(Long id);

    User findByUsername(String name);

    User findByEmail(String email);

    User save(User user);

//    void setInventoryFalse(User user);
//
//    void setInventoryTrue(User user);

//    void setActive(User user);

    void setStartMoney(User user);

    void userSetMoneyFromItem(User user, Long id);

    void userRemoveMoneyFromInventory(User user, Long id);

    List<User> findAll();

    User getOne(Long id);

    List<User> users();
}
