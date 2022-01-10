package by.itstep.shop.service;


import by.itstep.shop.dao.model.Inventory;
import by.itstep.shop.dao.model.Item;

public interface ItemService {
    Iterable<Item> findAllByInventory(Inventory inventory);
    void deleteItem(Item item);


    Item findItemById(Long id);

    void save(Item item);

     void createItemsFromInventory(Inventory inventory);
}
