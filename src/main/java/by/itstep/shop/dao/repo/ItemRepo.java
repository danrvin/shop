package by.itstep.shop.dao.repo;

import by.itstep.shop.dao.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepo extends JpaRepository<Item, Long> {
    Item findItemById(Long id);
}
