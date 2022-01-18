package by.itstep.shop.service;


import by.itstep.shop.dao.model.Item;
import by.itstep.shop.dao.model.Order;

import java.util.List;

public interface ItemService {
    void deleteItem(Item item);


    void putItemInBasket(Long itemId);

    Item findItemById(Long id);

    List<Item> findAllByOrder(Order order);

    void save(Item item);


}
