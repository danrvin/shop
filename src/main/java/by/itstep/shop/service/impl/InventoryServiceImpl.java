package by.itstep.shop.service.impl;


import by.itstep.shop.dao.model.Inventory;
import by.itstep.shop.dao.model.Item;
import by.itstep.shop.dao.model.User;
import by.itstep.shop.dao.repo.InventoryRepo;
import by.itstep.shop.dao.repo.ItemRepo;
import by.itstep.shop.service.InventoryService;
import by.itstep.shop.service.ItemService;
import by.itstep.shop.service.exceptions.NotFoundInventoryException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {


    private final ItemRepo itemRepo;
    private final InventoryRepo inventoryRepo;
    private final ItemService itemService;
    private int counter;

    public InventoryServiceImpl(ItemRepo itemRepo, InventoryRepo inventoryRepo, ItemService itemService) {
        this.itemRepo = itemRepo;
        this.inventoryRepo = inventoryRepo;
        this.itemService = itemService;
    }

    @Override
    public void create(String name, User user) {
        Inventory inventory = new Inventory(name, user);
        inventoryRepo.save(inventory);
    }

    @Override
    public Iterable<Inventory> findAll() {
        return inventoryRepo.findAll();
    }

    @Override
    public Inventory findInventoryById(Long id) {
        Inventory inventory = inventoryRepo.findInventoryById(id);
        if (inventory != null) {
            return inventory;
        } else {
            throw new NotFoundInventoryException("error id");
        }
    }

    @Override
    public void save(Inventory inventory) {
        inventoryRepo.save(inventory);
    }

    @Override
    public Inventory findByAuthor(User user) {
        return inventoryRepo.findByAuthor(user);
    }

    @Override
    public void setAuthor(Inventory inventory, User user) {
        inventory.setAuthor(user);
    }

    @Override
    public void deleteAuthor(Inventory inventory) {
        inventory.setAuthor(null);
    }


    @Override
    public void createInventories() {
        int number = (int) (Math.random() * 6) + 1;
        for (int i = 0; i < number; i++) {
            Inventory inventory = new Inventory();
            inventory.setName("Inventory" + counter);
            counter++;
            inventoryRepo.save(inventory);
            itemService.createItemsFromInventory(inventory);
            setPrice(inventory);
        }
    }

    @Override
    public void deleteIfNull(Inventory inventory) {
        List<Item> items = itemRepo.findAllByInventory(inventory);
        if (items.size() == 0) {
            inventoryRepo.delete(inventory);
        }
    }

    private void setPrice(Inventory inventory) {
        List<Item> items = itemRepo.findAllByInventoryOrderByPriseDesc(inventory);
        Item first = items.get(0);
        Item last = items.get(items.size() - 1);

        Long firstPrise = first.getPrise();
        Long lastPrice = last.getPrise();


        long x = (int)(firstPrise + Math.random() *
                Math.abs(firstPrise - lastPrice));
        inventory.setPrice(x * 3);
    }
}
