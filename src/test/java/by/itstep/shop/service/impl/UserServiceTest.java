package by.itstep.shop.service.impl;

import by.itstep.shop.dao.model.User;
import by.itstep.shop.dao.repo.UserRepo;
import by.itstep.shop.service.UserService;
import by.itstep.shop.service.exceptions.InvalidUserPasswordException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
        userService.updateUser(userFromDb,user);
    }



}
