package by.itstep.shop.service;

public interface OrderService {
    void putInBasket(Long itemId, String username);

    void deleteInBasket(Long itemId);

    void deleteAllInBasket(String username);

    void buyItemsInBasket(String username);
}
