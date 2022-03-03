package by.itstep.shop.service.impl;

import by.itstep.shop.dao.model.Item;
import by.itstep.shop.dao.model.User;
import by.itstep.shop.dao.repo.BasketRepo;
import by.itstep.shop.dao.repo.ItemRepo;
import by.itstep.shop.service.BasketService;
import by.itstep.shop.service.exceptions.NotEnoughMoneyException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BasketServiceImpl {

    @InjectMocks
    private BasketService basketService;

    @Mock
    private BasketRepo basketRepo;

    @Mock
    private ItemRepo itemRepo;


    @Test
    public void buyItemInBasket_Success() {
        Long itemId = 1L;
        User user = new User();
        user.setMoney(4123412.3);
        Item item = itemRepo.findItemById(itemId);
        item.setPrice(423.43);
        Double money = user.getMoney();
        user.setMoney(money - item.getPrice());
        itemRepo.delete(item);
        basketService.buyItemInBasket(itemId,user);
    }
}
