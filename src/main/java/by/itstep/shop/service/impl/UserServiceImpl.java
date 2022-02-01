package by.itstep.shop.service.impl;


import by.itstep.shop.dao.model.Basket;
import by.itstep.shop.dao.model.Item;
import by.itstep.shop.dao.model.User;
import by.itstep.shop.dao.model.enums.Role;
import by.itstep.shop.dao.model.enums.Status;
import by.itstep.shop.dao.repo.ItemRepo;
import by.itstep.shop.dao.repo.BasketRepo;
import by.itstep.shop.dao.repo.UserRepo;
import by.itstep.shop.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {


    private final UserRepo userRepo;
    private final BasketRepo basketRepo;


    public UserServiceImpl(UserRepo userRepo, BasketRepo basketRepo) {
        this.userRepo = userRepo;
        this.basketRepo = basketRepo;
    }

    @Override
    public User loadUserByUsername(String username){
        return userRepo.findByUsername(username);
    }

    @Override
    public User findByUserId(Long id) {
        return userRepo.findById(id).get();
    }

    @Override
    public User findByUsername(String name) {
        return userRepo.findByUsername(name);
    }

    @Override
    public Optional<User> findByUsernameOptional(String name) {
        return userRepo.findUserByUsername(name);
    }

    @Override
    public User save(User user) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        user.setStatus(Status.ACTIVE);
        user.setMoney(400.0);
        userRepo.save(user);
        Basket basket = new Basket();
        basket.setUser(user);
        basketRepo.save(basket);
        return userRepo.save(user);
    }

    @Override
    public void deleteUser(User user) {
        userRepo.delete(user);
    }

    @Override
    public void setStartMoney(User user) {
        user.setMoney(400.0);
    }

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public List<User> users() {
        return userRepo.findAll();
    }

    @Override
    public User updateUser(User userFromDb, User user) {
        userFromDb.setPassword(user.getPassword());
        if (user.getStatus() != null) {
            userFromDb.setStatus(user.getStatus());
        }
        userFromDb.setEmail(user.getEmail());
        userFromDb.setRole(user.getRole());
        userRepo.save(userFromDb);
        return userFromDb;
    }

    @Override
    public User addMoney(Double money, User user) {
        Double userMoney = user.getMoney();
        Double finalMoney = userMoney + money;
        user.setMoney(finalMoney);
        return userRepo.save(user);
    }
}
