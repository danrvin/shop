package by.itstep.shop.service.impl;


import by.itstep.shop.dao.model.Basket;
import by.itstep.shop.dao.model.User;
import by.itstep.shop.dao.model.enums.Role;
import by.itstep.shop.dao.model.enums.Status;
import by.itstep.shop.dao.repo.BasketRepo;
import by.itstep.shop.dao.repo.UserRepo;
import by.itstep.shop.service.UserService;
import by.itstep.shop.service.exceptions.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {


    private final UserRepo userRepo;
    private final BasketRepo basketRepo;


    public UserServiceImpl(UserRepo userRepo, BasketRepo basketRepo) {
        this.userRepo = userRepo;
        this.basketRepo = basketRepo;
    }

    @Override
    public User findByUsername(String name) {
        User user = userRepo.findByUsername(name);
        if (user == null) {
            throw new InvalidUserException("user not found");
        } else return user;
    }


    @Override
    public User save(User user) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        user.setStatus(Status.ACTIVE);
        user.setMoney(0.0);
        userRepo.save(user);
        Basket basket = new Basket();
        basket.setUser(user);
        basketRepo.save(basket);
        return userRepo.save(user);
    }


    @Override
    public void updateUser(User userFromDb, User user) {
        userFromDb.setStatus(user.getStatus());
        if (user.getPassword() == null) {
            throw new InvalidUserPasswordException("invalid password");
        } else {
            userFromDb.setPassword(user.getPassword());
        }
        userFromDb.setEmail(user.getEmail());
        userFromDb.setRole(user.getRole());
        userRepo.save(userFromDb);
    }

    @Override
    public void addMoney(Double money, User user) {
        Double userMoney = user.getMoney();
        if (money > 0) {
            Double finalMoney = userMoney + money;
            user.setMoney(finalMoney);
        } else {
            throw new MoneyException("Money can't be null or negative number");
        }
        userRepo.save(user);
    }
}
