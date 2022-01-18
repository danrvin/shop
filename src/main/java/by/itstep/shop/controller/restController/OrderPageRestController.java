package by.itstep.shop.controller.restController;

import by.itstep.shop.dao.model.Item;
import by.itstep.shop.service.OrderService;
import by.itstep.shop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;
import java.util.List;

@Controller
public class OrderPageRestController {

    private final OrderService orderService;
    private final UserService userService;

    public OrderPageRestController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }


    @DeleteMapping("/deleteInOrder")
    public List<Item> deleteInOrder(@RequestBody Long itemId, Principal principal) {
        orderService.deleteInOrder(itemId);
        return orderService.allItemsInOrder(userService.findByUsername(principal.getName()));
    }

    @PostMapping("/deleteAllItemsInOrder")
    public List<Item> deleteAllInBasket(Principal principal) {
        orderService.deleteAllInOrder(userService.findByUsername(principal.getName()));
        return orderService.allItemsInOrder(userService.findByUsername(principal.getName()));
    }

    @PostMapping("/buyItemsInBasket")
    public String buyItemsInBasket(Principal principal) {
//        orderService.buyItemsInBasket(principal.getName());
        return "correct";
    }
}
