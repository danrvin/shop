package by.itstep.shop.controller.restController;

import by.itstep.shop.service.BasketService;
import by.itstep.shop.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class BasketRestController {

    private final BasketService basketService;
    private final UserService userService;

    public BasketRestController(BasketService basketService, UserService userService) {
        this.basketService = basketService;
        this.userService = userService;
    }
}
