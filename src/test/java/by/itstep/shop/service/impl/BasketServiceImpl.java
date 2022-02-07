package by.itstep.shop.service.impl;

import by.itstep.shop.dao.model.Basket;
import by.itstep.shop.dao.model.Item;
import by.itstep.shop.dao.model.User;
import by.itstep.shop.dao.repo.BasketRepo;
import by.itstep.shop.dao.repo.ItemRepo;
import by.itstep.shop.service.BasketService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class BasketServiceImpl {

    @InjectMocks
    private BasketService basketService;

    @Mock
    private BasketRepo basketRepo;

    @Mock
    private ItemRepo itemRepo;

//    @Test
//    public List<Item> allItemsInBasket(User user) {
//        Basket basket =
//        return itemRepo.findAllByBasket(basket);
//    }
}
