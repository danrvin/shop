package by.itstep.shop.dao.repo;


import by.itstep.shop.dao.model.Inventory;
import by.itstep.shop.dao.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepo extends JpaRepository<Inventory, Long> {
    Inventory findByAuthor(User user);
    Inventory findInventoryById(Long id);
}
