package by.itstep.shop.service.impl;

import by.itstep.shop.dao.model.Basket;
import by.itstep.shop.dao.model.User;
import by.itstep.shop.dao.repo.BasketRepo;
import by.itstep.shop.dao.repo.UserRepo;
import by.itstep.shop.service.exceptions.InvalidUserException;
import by.itstep.shop.service.exceptions.InvalidUserPasswordException;
import by.itstep.shop.service.exceptions.MoneyException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepo userRepo;

    @Test
    public void updateUser_ErrorPasswordException() {
        User user = new User();
        User userFromDb = new User();
        userFromDb.setPassword(user.getPassword());
        Assertions.assertThrows(InvalidUserPasswordException.class, () -> userService.updateUser(user, userFromDb));
    }

    @Test
    public void updateUser_Success() {
        User user = new User();
        user.setPassword("123123");
        User userFromDb = new User();
        userFromDb.setPassword(user.getPassword());
        userFromDb.setStatus(user.getStatus());
        userFromDb.setMoney(user.getMoney());
        userFromDb.setRole(user.getRole());
        userService.updateUser(userFromDb, user);
    }

    @Test
    public void addMoney_success() {
        User user = new User();
        user.setMoney(313.3);
        double money = 33.3;
        userService.addMoney(money, user);
    }

    @Test
    public void addMoney_MoneyException() {
        User user = new User();
        user.setMoney(313.3);
        double money = -231;
        Assertions.assertThrows(MoneyException.class, () -> userService.addMoney(money, user));
    }

    @Test
    public void findByUserName_Success() {
        User user = new User();
        user.setUsername("sad");
        Mockito.when(userRepo.findByUsername(user.getUsername())).thenReturn(user);
        userService.findByUsername(user.getUsername());
    }

    @Test
    public void findByUserName_UserNotFoundException() {
        User user = new User();
        Assertions.assertThrows(InvalidUserException.class, () -> userService.findByUsername(user.getUsername()));
    }

    @Test
    public void save() {
        User user = new User();
        user.setPassword("123");
        Basket basket = new Basket();
        basket.setUser(user);
        userService.save(user);
    }
}
