package by.itstep.shop.dao.repo;

import by.itstep.shop.dao.model.Order;
import by.itstep.shop.dao.model.User;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {
    Order findByUser(User user);
}
