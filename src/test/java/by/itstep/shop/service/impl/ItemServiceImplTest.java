package by.itstep.shop.service.impl;

import by.itstep.shop.dao.model.Item;
import by.itstep.shop.dao.repo.BasketRepo;
import by.itstep.shop.dao.repo.ItemRepo;
import by.itstep.shop.service.BasketService;
import by.itstep.shop.service.ItemService;
import by.itstep.shop.service.exceptions.InvalidItemException;
import by.itstep.shop.service.exceptions.NotEnoughMoneyException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ItemServiceImplTest {


    @InjectMocks
    private ItemServiceImpl itemService;

    @Mock
    private BasketRepo basketRepo;

    @Mock
    private ItemRepo itemRepo;

    @Test
    public void save_Success() {
        Item item = new Item();
        Mockito.when(itemRepo.findItemById(1L)).thenReturn(item);
        itemRepo.findItemById(1L);

    }

    @Test
    public void updateItem_Success() {
        Item itemFromDb = new Item();
        Item item = new Item();
        item.setDescription("hrgvew");
        item.setName("hrwmk");
        item.setPrice(6343.6);
        itemFromDb.setName(item.getName());
        itemFromDb.setDescription(item.getDescription());
        itemFromDb.setPrice(item.getPrice());
        itemService.updateItem(itemFromDb, item);
    }

    @Test
    public void updateItem_Exception() {
        Item itemFromDb = new Item();
        Item item = new Item();
        item.setDescription("hrgvew");
        item.setName("hrwmk");
        item.setPrice(-6343.6);
        itemFromDb.setName(item.getName());
        itemFromDb.setDescription(item.getDescription());
        itemFromDb.setPrice(item.getPrice());
        Assertions.assertThrows(InvalidItemException.class, () -> itemService.updateItem(itemFromDb, item));
    }

    @Test
    public List<Item> sortItemByPrice_Success() {

        double startPrise = 100.0;
        double lastPrice = 2000.0;
        List<Item> sortItems = new ArrayList<>();
        List<Item> allItems = itemRepo.findItemsByPrise(startPrise, lastPrice);
        for (Item item : allItems) {
            if (item.getPrice() >= startPrise && item.getPrice() <= lastPrice) {
                sortItems.add(item);
            }
        }
        return sortItems;
    }


    public List<Item> sortItemByPrice_Success(Double startPrise, Double lastPrice) {
        List<Item> sortItems = new ArrayList<>();
        if (startPrise < 0 || lastPrice < 0) {
            return itemRepo.findAll();
        }
        if (startPrise > lastPrice) {
            double price = startPrise;
            startPrise = lastPrice;
            lastPrice = price;
        }
        List<Item> allItems = itemRepo.findItemsByPrise(startPrise, lastPrice);
        for (Item item : allItems) {
            if (item.getPrice() >= startPrise && item.getPrice() <= lastPrice) {
                sortItems.add(item);
            }
        }
        return sortItems;
    }
}
