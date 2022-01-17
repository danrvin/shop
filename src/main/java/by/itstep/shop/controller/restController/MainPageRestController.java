package by.itstep.shop.controller.restController;

import by.itstep.shop.service.ItemService;
import by.itstep.shop.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/main")
public class MainPageRestController {

    private final OrderService orderService;

    public MainPageRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/basket")
    public String basket() {
        return "basket";
    }

    @PostMapping("/putInBasket")
    public String InBasket(@RequestBody Long itemId, Principal principal) {
        orderService.putInBasket(itemId,principal.getName());
        return "correct";
    }

    @PostMapping("/deleteInBasket")
    public String deleteInBasket(@RequestBody Long itemId) {
        orderService.deleteInBasket(itemId);
        return "correct";
    }
}
