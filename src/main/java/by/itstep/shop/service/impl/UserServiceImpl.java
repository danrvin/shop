package by.itstep.shop.service.impl;


import by.itstep.shop.dao.model.Inventory;
import by.itstep.shop.dao.model.Item;
import by.itstep.shop.dao.model.User;
import by.itstep.shop.dao.repo.InventoryRepo;
import by.itstep.shop.dao.repo.ItemRepo;
import by.itstep.shop.dao.repo.UserRepo;
import by.itstep.shop.service.UserService;
import by.itstep.shop.service.exceptions.NotEnoughMoneyException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {


    private final UserRepo userRepo;
    private final ItemRepo itemRepo;
    private final InventoryRepo inventoryRepo;

    public UserServiceImpl(UserRepo userRepo, ItemRepo itemRepo, InventoryRepo inventoryRepo) {
        this.userRepo = userRepo;
        this.itemRepo = itemRepo;
        this.inventoryRepo = inventoryRepo;
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
    public User findByEmail(String email) {
        return userRepo.findByEmail(email).get();
    }

    @Override
    public User save(User user) {
        return userRepo.save(user);
    }

    @Override
    public void setStartMoney(User user) {
        user.setMoney(400L);
    }

    @Override
    public void userSetMoneyFromItem(User user, Long id) {
        Item item = itemRepo.findItemById(id);
        Long moneys = user.getMoney();
        Long prise = item.getPrise();
        Long wallet = moneys + prise;
        user.setMoney(wallet);
        userRepo.save(user);
    }

    @Override
    public void userRemoveMoneyFromInventory(User user, Long id) {
        Inventory inventory = inventoryRepo.findInventoryById(id);
        Long price = inventory.getPrice();
        Long moneys = user.getMoney();
        if (moneys >= price) {
            Long wallet = moneys - price;
            user.setMoney(wallet);
            userRepo.save(user);
        } else {
            throw new NotEnoughMoneyException("you dont have enough money");
        }
    }

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public User getOne(Long id) {
        return userRepo.findUserById(id);
    }

    @Override
    public List<User> users() {
        return userRepo.findAll();
    }
}
