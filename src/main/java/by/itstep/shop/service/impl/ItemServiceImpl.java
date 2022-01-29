package by.itstep.shop.service.impl;

import by.itstep.shop.dao.model.Item;
import by.itstep.shop.dao.model.Basket;
import by.itstep.shop.dao.repo.ItemRepo;
import by.itstep.shop.service.ItemService;
import by.itstep.shop.service.exceptions.NotFoundItemException;
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
    public void putItemInBasket(Long itemId) {
        Item item = itemRepo.findItemById(itemId);
        itemRepo.save(item);
    }

    @Override
    public Item findItemById(Long id) {
        Item item = itemRepo.findItemById(id);
        if (item != null) {
            return item;
        } else {
            throw new NotFoundItemException("error item id");
        }
    }

    @Override
    public List<Item> findAllByOrder(Basket basket) {
        return itemRepo.findAllByBasket(basket);
    }

    @Override
    public void save(Item item) {
        itemRepo.save(item);
    }

    @Override
    public List<Item> getAll() {
        return itemRepo.findAll();
    }

    @Override
    public Item updateItem(Item itemFromDb, Item item) {
        itemFromDb.setName(item.getName());
        itemFromDb.setDescription(item.getDescription());
        itemFromDb.setPrise(item.getPrise());
        itemRepo.save(itemFromDb);
        return itemFromDb;
    }

    @Override
    public List<Item> sortItemByPrice(Double startPrise, Double lastPrice) {
        List<Item> allItems = itemRepo.findAll();
        List<Item> sortItems = new ArrayList<>();
        if (startPrise < 0 && lastPrice < 0) {
            return itemRepo.findAll();
        }
        if (startPrise > lastPrice) {
            return itemRepo.findAll();
        }
        for (Item item : allItems) {
            if (item.getPrise() >= startPrise && item.getPrise() <= lastPrice) {
                sortItems.add(item);
            }
        }
        return sortItems;
    }
}
