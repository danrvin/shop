package by.itstep.shop.controller.restController;

import by.itstep.shop.dao.model.Item;
import by.itstep.shop.service.ItemService;
import by.itstep.shop.service.BasketService;
import by.itstep.shop.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping("/{id}")
    @PreAuthorize(value = "hasAuthority('item:read')")
    public Item item(@PathVariable Long id) {
        return itemService.findItemById(id);
    }

    @GetMapping("/all")
    @PreAuthorize(value = "hasAuthority('item:read')")
    public List<Item> items() {
        return itemService.getAll();
    }

    @PostMapping("/create")
    @PreAuthorize(value = "hasAuthority('item:write')")
    public Item createItem(@RequestBody Item item) {
        itemService.save(item);
        return item;
    }

    @DeleteMapping("/item/{id}")
    @PreAuthorize(value = "hasAuthority('item:write')")
    public List<Item> deleteInOrder(@PathVariable Long id, Principal principal) {
        basketService.deleteInBasket(id);
        return basketService.allItemsInBasket(userService.findByUsername(principal.getName()));
    }

    @PutMapping("/update/{id}")
    @PreAuthorize(value = "hasAuthority('item:write')")
    public Item updateItem(@PathVariable("id") Item itemFromDb, @RequestBody Item item) {
        itemService.updateItem(itemFromDb, item);
        return item;
    }
}