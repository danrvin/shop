package by.itstep.shop.controller.restController;

import by.itstep.shop.dao.model.Item;
import by.itstep.shop.service.BasketService;
import by.itstep.shop.service.ItemService;
import by.itstep.shop.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/basket")
public class BasketRestController {

    private final BasketService basketService;
    private final UserService userService;

    public BasketRestController(BasketService basketService, UserService userService) {
        this.basketService = basketService;
        this.userService = userService;
    }

    @PostMapping("/put/{id}")
    @PreAuthorize(value = "hasAuthority('basket:write')")
    public List<Item> InBasket(@PathVariable Long id, Principal principal) {
        basketService.putInBasket(id, userService.findByUsername(principal.getName()));
        return basketService.allItemsInBasket(userService.findByUsername(principal.getName()));
    }

    @DeleteMapping("/items")
    @PreAuthorize(value = "hasAuthority('basket:read')")
    public List<Item> deleteAllInBasket(Principal principal) {
        basketService.deleteAllInBasket(userService.findByUsername(principal.getName()));
        return basketService.allItemsInBasket(userService.findByUsername(principal.getName()));
    }

    @DeleteMapping("/item/{id}")
    @PreAuthorize(value = "hasAuthority('basket:write')")
    public List<Item> deleteItemInBasket(@PathVariable Long id, Principal principal) {
        basketService.deleteInBasket(id);
        return basketService.allItemsInBasket(userService.findByUsername(principal.getName()));
    }
}
