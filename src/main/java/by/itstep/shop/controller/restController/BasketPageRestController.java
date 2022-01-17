package by.itstep.shop.controller.restController;

import by.itstep.shop.service.OrderService;
import by.itstep.shop.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Controller
public class BasketPageRestController {

    private final OrderService orderService;
    private final UserService userService;

    public BasketPageRestController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @PostMapping("/putInBasket")
    public ResponseEntity<?> InBasket(@RequestBody Long itemId, Principal principal) {
        orderService.putInBasket(itemId,principal.getName());
        Map<Object,Object> model = new HashMap<>();
        model.put("result","ok");
        return ResponseEntity.ok(model);
    }

    @PostMapping("/deleteInBasket")
    public String deleteInBasket(@RequestBody Long itemId) {
        orderService.deleteInBasket(itemId);
        return "correct";
    }

    @PostMapping("/deleteAllItemsInBasket")
    public String deleteAllInBasket(Principal principal) {
        orderService.deleteAllInBasket(principal.getName());
        return "correct";
    }

    @PostMapping("/buyItemsInBasket")
    public String buyItemsInBasket(Principal principal) {
        orderService.buyItemsInBasket(principal.getName());
        return "correct";
    }
}
