package by.itstep.shop.service;


import by.itstep.shop.dao.model.Item;

import java.util.List;

public interface ItemService {

    Item save(Item item);

    Item findItemById(Long id);


    List<Item> getAll();

    Item updateItem(Item itemFromDb, Item item);


    List<Item> sortItemByPrice(Double firstPrise, Double lastPrice);

}
