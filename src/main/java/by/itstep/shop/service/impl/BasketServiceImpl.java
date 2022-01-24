package by.itstep.shop.service.impl;

import by.itstep.shop.dao.model.Item;
import by.itstep.shop.dao.model.Basket;
import by.itstep.shop.dao.model.User;
import by.itstep.shop.dao.repo.ItemRepo;
import by.itstep.shop.dao.repo.BasketRepo;
import by.itstep.shop.dao.repo.UserRepo;
import by.itstep.shop.service.BasketService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasketServiceImpl implements BasketService {

    private final BasketRepo basketRepo;
    private final ItemRepo itemRepo;
    private final UserRepo userRepo;

    public BasketServiceImpl(BasketRepo basketRepo, ItemRepo itemRepo, UserRepo userRepo) {
        this.basketRepo = basketRepo;
        this.itemRepo = itemRepo;
        this.userRepo = userRepo;
    }

    @Override
    public void deleteInBasket(Long itemId) {
        Item item = itemRepo.findItemById(itemId);
        item.setBasket(null);
        itemRepo.save(item);
    }

    @Override
    public List<Item> allItemsInBasket(User user) {
        Basket basket = basketRepo.findByUser(user);
        return itemRepo.findAllByBasket(basket);
    }

    @Override
    public void putInBasket(Long id, User user) {
        Item item = itemRepo.findItemById(id);
        Basket basket = basketRepo.findByUser(user);
        item.setBasket(basket);
        itemRepo.save(item);
    }

    @Override
    public void deleteAllInBasket(User user) {
        Basket basket = basketRepo.findByUser(user);
        List<Item> items = itemRepo.findAllByBasket(basket);
        for (Item item : items) {
            item.setBasket(null);
            itemRepo.save(item);
        }
    }

    @Override
    public void buyItemInBasket(Long itemId, User user) {
        Item item = itemRepo.findItemById(itemId);
        Long money = user.getMoney();
        user.setMoney(money - item.getPrise());
        itemRepo.delete(item);
        userRepo.save(user);
    }
}
