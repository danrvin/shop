package by.itstep.shop.controller.restController;

import by.itstep.shop.dao.model.Item;
import by.itstep.shop.service.BasketService;
import by.itstep.shop.service.ItemService;
import by.itstep.shop.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/basket")
public class BasketRestController {

    private final Logger logger = LoggerFactory.getLogger(ItemRestController.class);

    private final BasketService basketService;
    private final UserService userService;
    private final ItemService itemService;

    public BasketRestController(BasketService basketService, UserService userService, ItemService itemService) {

        this.basketService = basketService;
        this.userService = userService;
        this.itemService = itemService;
    }


    @GetMapping("/")
    @PreAuthorize(value = "hasAuthority('basket:read')")
    public ResponseEntity<?> items(Principal principal) {
        try {
            return ResponseEntity.ok().body(
                    basketService.allItemsInBasket(
                            userService.findByUsername(principal.getName())));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping("/put")
    @PreAuthorize(value = "hasAuthority('basket:read')")
    public ResponseEntity<?> InBasket(@RequestParam Long id, Principal principal) {
        try {
            basketService.putInBasket(id, userService.findByUsername(principal.getName()));
            logger.info("added in basket:" + itemService.findItemById(id).getName());
            return ResponseEntity.ok().body(
                    basketService.allItemsInBasket(
                            userService.findByUsername(principal.getName())));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @DeleteMapping("/items")
    @PreAuthorize(value = "hasAuthority('basket:write')")
    public ResponseEntity<?> deleteAllInBasket(Principal principal) {
        try {
            basketService.deleteAllInBasket(userService.findByUsername(principal.getName()));
            logger.info("All items removed");
            return ResponseEntity.ok().body(
                    basketService.allItemsInBasket(
                            userService.findByUsername(principal.getName())));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @DeleteMapping("/item")
    @PreAuthorize(value = "hasAuthority('basket:write')")
    public ResponseEntity<?> deleteItemInBasket(@RequestParam Long id, Principal principal) {
        try {
            basketService.deleteInBasket(id, userService.findByUsername(principal.getName()));
            logger.info("Item removed:" + itemService.findItemById(id).getName());
            return ResponseEntity.ok().body(
                    basketService.allItemsInBasket(
                            userService.findByUsername(principal.getName())));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
