package by.itstep.shop.dao.repo;

import by.itstep.shop.dao.model.Inventory;
import by.itstep.shop.dao.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepo extends JpaRepository<Item, Long> {
    List<Item> findAllByInventory(Inventory inventory);
    List<Item> findAllByInventoryOrderByPriseDesc(Inventory inventory);
    Item findItemById(Long id);
}
