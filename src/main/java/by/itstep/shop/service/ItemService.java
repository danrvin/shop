package by.itstep.shop.service;


import by.itstep.shop.dao.model.Item;
import by.itstep.shop.dao.model.Basket;

import java.util.List;

public interface ItemService {
    void deleteItem(Item item);


    void putItemInBasket(Long itemId);

    Item findItemById(Long id);

    List<Item> findAllByOrder(Basket basket);

    void save(Item item);


    List<Item> getAll();

    Item updateItem(Item itemFromDb, Item item);
}
