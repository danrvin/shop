package by.itstep.shop.service.impl;

import by.itstep.shop.dao.model.Item;
import by.itstep.shop.dao.repo.ItemRepo;
import by.itstep.shop.service.exceptions.InvalidItemException;
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
    private ItemRepo itemRepo;

    @Test
    public void saveItem_Success() {
        Item item = new Item();
        Mockito.when(itemRepo.findItemById(1L)).thenReturn(item);
        itemRepo.findItemById(1L);
    }

    @Test
    public void getAllItem_Success() {
        List<Item> items = new ArrayList<>();
        Item item = new Item();
        Item item1 = new Item();
        items.add(item);
        items.add(item1);
        Mockito.when(itemRepo.findAll()).thenReturn(items);
        itemRepo.findAll();
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
    public void sortItemByPrice_Success() {

        double startPrise = 100.0;
        double lastPrice = 2000.0;
        List<Item> sortItems = new ArrayList<>();
        List<Item> allItems = itemRepo.findItemsByPrise(startPrise, lastPrice);
        for (Item item : allItems) {
            if (item.getPrice() >= startPrise && item.getPrice() <= lastPrice) {
                sortItems.add(item);
            }
        }
        itemService.sortItemByPrice(startPrise, lastPrice);
    }


    @Test
    public void sortItemByPrice_NegativePrice() {
        double startPrise = -100.342;
        double lastPrice = -41.42;
        Assertions.assertThrows(InvalidItemException.class, () -> itemService.sortItemByPrice(startPrise,lastPrice));
    }

    @Test
    public void sortedItemByPrice_SuccessWithChangingPrice() {
        double startPrise = 2000.0;
        double lastPrice = 100.0;
        if (startPrise > lastPrice) {
            double price = startPrise;
            startPrise = lastPrice;
            lastPrice = price;
        }
        itemService.sortItemByPrice(startPrise,lastPrice);
    }



}
