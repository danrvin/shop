package by.itstep.shop.service;


import by.itstep.shop.dao.model.Item;
import by.itstep.shop.dao.model.Basket;

import java.util.List;

public interface ItemService {
    void deleteItem(Item item);

    Item save(Item item);

    Item findItemById(Long id);


    List<Item> getAll();

    Item updateItem(Item itemFromDb, Item item);


    List<Item> sortItemByPrice(Double firstPrise, Double lastPrice);

}
