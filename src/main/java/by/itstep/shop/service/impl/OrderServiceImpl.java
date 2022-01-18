package by.itstep.shop.service.impl;

import by.itstep.shop.dao.model.Item;
import by.itstep.shop.dao.model.Order;
import by.itstep.shop.dao.model.User;
import by.itstep.shop.dao.repo.ItemRepo;
import by.itstep.shop.dao.repo.OrderRepo;
import by.itstep.shop.dao.repo.UserRepo;
import by.itstep.shop.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;
    private final ItemRepo itemRepo;
    private final UserRepo userRepo;

    public OrderServiceImpl(OrderRepo orderRepo, ItemRepo itemRepo, UserRepo userRepo) {
        this.orderRepo = orderRepo;
        this.itemRepo = itemRepo;
        this.userRepo = userRepo;
    }

    @Override
    public void deleteInOrder(Long itemId) {
        Item item = itemRepo.findItemById(itemId);
        item.setOrder(null);
        itemRepo.save(item);
    }

    @Override
    public List<Item> allItemsInOrder(User user) {
        Order order = orderRepo.findByUser(user);
        return itemRepo.findAllByOrder(order);
    }

    @Override
    public void deleteAllInOrder(User user) {
        Order order = orderRepo.findByUser(user);
        List<Item> items = itemRepo.findAllByOrder(order);
        for (Item item: items) {
            item.setOrder(null);
        }
    }

//    @Override
//    public void putInBasket(Long itemId, User user) {
//        Order order = new Order();
//        Item item = itemRepo.findItemById(itemId);
//        item.setOrder(order);
//        order.setUser(user);
//        orderRepo.save(order);
//    }
//
//    @Override
//    public void deleteInOrder(Long itemId) {
//        Order order = orderRepo.findOrderByItemId(itemId);
//        orderRepo.delete(order);
//    }
//
//    @Override
//    public void deleteAllInBasket(String username) {
//        orderRepo.deleteAllByUsername(username);
//    }
//
//    @Override
//    public void buyItemsInBasket(String username) {
//        List<Order> orders = orderRepo.findAllByUsername(username);
//
//        Long price = 0L;
//
//        for(Order order : orders) {
//            Long itemId = itemRepo.findItemById();
//            Item item = itemRepo.findItemById(itemId);
//            price += item.getPrise();
//        }
//
//        User user = userRepo.findByUsername(username);
//        Long money = user.getMoney();
//        Long remainder = money - price;
//        user.setMoney(remainder);
//        userRepo.save(user);
//    }
}
