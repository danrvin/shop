package by.itstep.shop.service.impl;

import by.itstep.shop.dao.model.Basket;
import by.itstep.shop.dao.model.Item;
import by.itstep.shop.dao.model.User;
import by.itstep.shop.dao.repo.BasketRepo;
import by.itstep.shop.dao.repo.ItemRepo;
import by.itstep.shop.service.exceptions.InvalidItemException;
import by.itstep.shop.service.exceptions.NotEnoughMoneyException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class BasketServiceTest {

    @InjectMocks
    private BasketServiceImpl basketService;

    @Mock
    private BasketRepo basketRepo;

    @Mock
    private ItemRepo itemRepo;

    @Test
    public void buyItemInBasket_Success() {
        Long itemId = 1L;
        User user = new User();
        user.setMoney(4123412.3);
        Item item = new Item();
        item.setPrice(423.43);
        Double money = user.getMoney();
        user.setMoney(money - item.getPrice());
        Mockito.when(itemRepo.findItemById(itemId)).thenReturn(item);
        basketService.buyItemInBasket(itemId,user);
    }

    @Test
    public void buyItemInBasket_NotEnoughMoney() {
        Long itemId = 1L;
        User user = new User();
        user.setMoney(1.1);
        Item item = new Item();
        item.setPrice(423.43);
        Double money = user.getMoney();
        user.setMoney(money - item.getPrice());
        Mockito.when(itemRepo.findItemById(itemId)).thenReturn(item);
        Assertions.assertThrows(NotEnoughMoneyException.class, () -> basketService.buyItemInBasket(itemId,user));
    }

    @Test
    public void deleteItemInBasket_Success() {
        User user = new User();
        Basket basket = new Basket();
        long itemId = 1;
        Item item = new Item();
        Mockito.when(basketRepo.findByUser(user)).thenReturn(basket);
        Mockito.when(itemRepo.findItemByIdAndBasketId(itemId,basket.getId())).thenReturn(item);
        basketService.deleteInBasket(itemId,user);
    }

    @Test
    public void deleteItemInBasket_ItemNotFoundException() {
        User user = new User();
        Basket basket = new Basket();
        long itemId = 1;
        Mockito.when(basketRepo.findByUser(user)).thenReturn(basket);
        Assertions.assertThrows(InvalidItemException.class, () -> basketService.deleteInBasket(itemId,user));
    }

    @Test
    public void allItemsInBasket_Success() {
        User user = new User();
        basketService.allItemsInBasket(user);
    }

    @Test
    public void putInBasket_Success() {
        User user = new User();
        long id = 1;
        Item item = new Item();
        Basket basket = new Basket();
        Mockito.when(itemRepo.findItemById(id)).thenReturn(item);
        Mockito.when(basketRepo.findByUser(user)).thenReturn(basket);
        basketService.putInBasket(id, user);
    }

    @Test
    public void putInBasket_InvalidItemException() {
        Item item = new Item();
        User user = new User();
        Assertions.assertThrows(InvalidItemException.class, () -> basketService.putInBasket(item.getId(),user));
    }

    @Test
    public void deleteAllInBasket_Success() {
        User user = new User();
        basketService.deleteAllInBasket(user);
    }
}
