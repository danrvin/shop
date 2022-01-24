package by.itstep.shop.dao.repo;

import by.itstep.shop.dao.model.Basket;
import by.itstep.shop.dao.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketRepo extends JpaRepository<Basket, Long> {
    Basket findByUser(User user);
}
