package by.itstep.shop.service.impl;

import by.itstep.shop.dao.model.Item;
import by.itstep.shop.dao.repo.ItemRepo;
import by.itstep.shop.service.ItemService;
import by.itstep.shop.service.exceptions.NotFoundItemException;
import org.springframework.stereotype.Service;

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
        item.setStatus("IN BASKET");
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
    public void save(Item item) {
        itemRepo.save(item);
    }
}
