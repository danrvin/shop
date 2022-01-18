package by.itstep.shop.service;

import by.itstep.shop.dao.model.Item;
import by.itstep.shop.dao.model.User;

import java.util.List;

public interface OrderService {
    void deleteInOrder(Long itemId);

    List<Item> allItemsInOrder(User user);

    void deleteAllInOrder(User user);
//    void putInBasket(Long itemId, User user);
//
//    void deleteInBasket(Long itemId);
//
//    void deleteAllInBasket(String username);
//
//    void buyItemsInBasket(String username);
}
