package by.itstep.shop.service.impl;

import by.itstep.shop.controller.restController.ItemRestController;
import by.itstep.shop.dao.model.Item;
import by.itstep.shop.dao.repo.ItemRepo;
import by.itstep.shop.service.ItemService;
import by.itstep.shop.service.exceptions.InvalidItemException;
import net.bytebuddy.implementation.bytecode.Throw;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {


    private final ItemRepo itemRepo;

    public ItemServiceImpl(ItemRepo itemRepo) {
        this.itemRepo = itemRepo;
    }


    @Override
    public Item findItemById(Long id) {
        Item item = itemRepo.findItemById(id);
        if (item == null) {
            throw new InvalidItemException("there is no item with this id");
        }
        return item;
    }

    @Override
    public Item save(Item item) {
        return itemRepo.save(item);
    }

    @Override
    public List<Item> getAll() {
        return itemRepo.findAll();
    }

    @Override
    public Item updateItem(Item itemFromDb, Item item) {
        itemFromDb.setName(item.getName());
        if (item.getPrice() <= 0) {
            throw new InvalidItemException("invalid money in item " + item.getName());
        }
        itemFromDb.setPrice(item.getPrice());
        itemRepo.save(itemFromDb);
        return itemFromDb;
    }

    @Override
    public List<Item> sortItemByPrice(Double startPrise, Double lastPrice) {
        List<Item> sortItems = new ArrayList<>();
        if (startPrise < 0 || lastPrice < 0) {
            throw new InvalidItemException("Wrong price to sort");
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
