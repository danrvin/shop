package by.itstep.shop.controller.restController;

import by.itstep.shop.dao.model.Item;
import by.itstep.shop.service.BasketService;
import by.itstep.shop.service.UserService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/transaction")
public class TransactionRestController {

    private final BasketService basketService;
    private final UserService userService;

    public TransactionRestController(BasketService basketService, UserService userService) {
        this.basketService = basketService;
        this.userService = userService;
    }

    @PostMapping("/{id}")
    public List<Item> buyItemsInBasket(@PathVariable Long id, Principal principal) {
        basketService.buyItemInBasket(id, userService.loadUserByUsername(principal.getName()));
        return basketService.allItemsInBasket(userService.findByUsername(principal.getName()));
    }
}
