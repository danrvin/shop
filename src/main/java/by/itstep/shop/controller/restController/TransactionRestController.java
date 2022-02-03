package by.itstep.shop.controller.restController;

import by.itstep.shop.dao.model.User;
import by.itstep.shop.service.BasketService;
import by.itstep.shop.service.ItemService;
import by.itstep.shop.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/transaction")
public class TransactionRestController {

    private final Logger logger = LoggerFactory.getLogger(ItemRestController.class);

    private final BasketService basketService;
    private final UserService userService;
    private final ItemService itemService;

    public TransactionRestController(BasketService basketService, UserService userService, ItemService itemService) {
        this.basketService = basketService;
        this.userService = userService;
        this.itemService = itemService;
    }

    @PostMapping("/money")
    public User updateMoney(@RequestBody Double money, Principal principal) {
        return userService.addMoney(money, userService.findByUsername(principal.getName()));
    }

    @PostMapping("/{id}")
    @PreAuthorize(value = "hasAuthority('transaction:buy')")
    public ResponseEntity<?> buyItemsInBasket(@PathVariable Long id, Principal principal) {
        try {
            basketService.buyItemInBasket(id, userService.loadUserByUsername(principal.getName()));
            logger.info("item sold:" + itemService.findItemById(id));
            return ResponseEntity.ok().body(
                    basketService.allItemsInBasket(
                            userService.findByUsername(principal.getName())));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
