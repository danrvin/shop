package by.itstep.shop.service.impl;

import by.itstep.shop.dao.model.Item;
import by.itstep.shop.dao.repo.ItemRepo;
import by.itstep.shop.service.ItemService;
import by.itstep.shop.service.exceptions.InvalidItemException;
import net.bytebuddy.implementation.bytecode.Throw;
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
    public void deleteItem(Item item) {
        itemRepo.delete(item);
    }

    @Override
    public Item findItemById(Long id) {
        return itemRepo.findItemById(id);
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
        itemFromDb.setDescription(item.getDescription());
        if (item.getPrice() <= 0) {
            throw new InvalidItemException("invalid item" + item.getName());
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
