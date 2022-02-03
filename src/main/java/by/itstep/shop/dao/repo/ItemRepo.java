package by.itstep.shop.dao.repo;

import by.itstep.shop.dao.model.Item;
import by.itstep.shop.dao.model.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.lang.annotation.Native;
import java.util.List;

public interface ItemRepo extends JpaRepository<Item, Long> {
    Item findItemById(Long id);

    Item findItemByIdAndBasketId(Long itemId, Long basketId);

    List<Item> findAllByBasket(Basket basket);


    @Query(nativeQuery = true, value = "SELECT * FROM ITEM WHERE PRICE between :firstPrice AND :lastPrice ORDER BY PRICE")
    List<Item> findItemsByPrise(Double firstPrice, Double lastPrice);

}
