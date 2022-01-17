package by.itstep.shop.dao.repo;

import by.itstep.shop.dao.model.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {
    Order findOrderByItemId(Long id);

    void deleteAllByUsername(String username);

    List<Order> findAllByUsername(String username);
}
