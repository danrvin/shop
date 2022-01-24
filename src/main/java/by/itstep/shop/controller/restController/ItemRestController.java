package by.itstep.shop.controller.restController;

import by.itstep.shop.dao.model.Item;
import by.itstep.shop.service.ItemService;
import by.itstep.shop.service.BasketService;
import by.itstep.shop.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/item")
public class ItemRestController {

    private final ItemService itemService;
    private final BasketService basketService;
    private final UserService userService;


    public ItemRestController(ItemService itemService, BasketService basketService, UserService userService) {
        this.itemService = itemService;
        this.basketService = basketService;
        this.userService = userService;
    }

    @GetMapping
    public List<Item> getAll() {
        return itemService.getAll();
    }

    @GetMapping("/{id}")
    public Item item(@PathVariable Long id) {
        return itemService.findItemById(id);
    }

    @PostMapping("/create")
    public Item createItem(@RequestBody Item item) {
        itemService.save(item);
        return item;
    }

    @PostMapping("/buy/{id}")
    public List<Item> buyItemsInBasket(@PathVariable Long id, Principal principal) {
        basketService.buyItemInBasket(id,userService.loadUserByUsername(principal.getName()));
        return basketService.allItemsInBasket(userService.findByUsername(principal.getName()));
    }


    @PostMapping("/put/{id}")
    public List<Item> InBasket(@PathVariable Long id, Principal principal) {
        basketService.putInBasket(id,userService.findByUsername(principal.getName()));
        return basketService.allItemsInBasket(userService.findByUsername(principal.getName()));
    }

    @DeleteMapping("/item/{id}")
    public List<Item> deleteInOrder(@PathVariable Long id, Principal principal) {
        basketService.deleteInBasket(id);
        return basketService.allItemsInBasket(userService.findByUsername(principal.getName()));
    }

    @DeleteMapping("/all")
    public List<Item> deleteAllInBasket(Principal principal) {
        basketService.deleteAllInBasket(userService.findByUsername(principal.getName()));
        return basketService.allItemsInBasket(userService.findByUsername(principal.getName()));
    }

    @DeleteMapping("/{id}")
    public List<Item> deleteItemInBasket(@PathVariable Long id, Principal principal) {
        basketService.deleteInBasket(id);
        return basketService.allItemsInBasket(userService.findByUsername(principal.getName()));
    }

}
