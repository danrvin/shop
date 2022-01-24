package by.itstep.shop.service;

import by.itstep.shop.dao.model.Item;
import by.itstep.shop.dao.model.User;

import java.util.List;

public interface BasketService {
    void deleteInBasket(Long itemId);

    List<Item> allItemsInBasket(User user);

    void putInBasket(Long id, User user);

    void deleteAllInBasket(User user);

    void buyItemInBasket(Long itemId, User user);
}
