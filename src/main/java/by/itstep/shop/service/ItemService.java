package by.itstep.shop.service;


import by.itstep.shop.dao.model.Item;

public interface ItemService {
    void deleteItem(Item item);


    void putItemInBasket(Long itemId);

    Item findItemById(Long id);

    void save(Item item);


}
