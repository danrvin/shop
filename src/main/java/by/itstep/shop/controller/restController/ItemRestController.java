package by.itstep.shop.controller.restController;

import by.itstep.shop.dao.model.Item;
import by.itstep.shop.service.ItemService;
import by.itstep.shop.service.BasketService;
import by.itstep.shop.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/item")
public class ItemRestController {


    private final Logger logger = LoggerFactory.getLogger(ItemRestController.class);

    private final ItemService itemService;
    private final BasketService basketService;
    private final UserService userService;


    public ItemRestController(ItemService itemService, BasketService basketService, UserService userService) {
        this.itemService = itemService;
        this.basketService = basketService;
        this.userService = userService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<Item> item(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(itemService.findItemById(id));
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return ResponseEntity.internalServerError().build();
    }


    @GetMapping("/all")
    public ResponseEntity<?> items() {
        try {
            return ResponseEntity.ok().body(itemService.getAll());
        } catch (Exception e) {
            logger.info(e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping("/create")
    @PreAuthorize(value = "hasAuthority('item:write')")
    public ResponseEntity<?> createItem(@RequestBody Item item) {
        try {
            Item newItem = itemService.save(item);
            logger.info("Create Item " + newItem.getName());
            return ResponseEntity.ok().body(itemService.save(item));

        } catch (Exception e) {
            logger.info(e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }



    @PutMapping("/update/{id}")
    @PreAuthorize(value = "hasAuthority('item:write')")
    public ResponseEntity<?> updateItem(@PathVariable("id") Item itemFromDb, @RequestBody Item item) {
        try {
            Item updateItem = itemService.updateItem(itemFromDb, item);
            logger.info("item update:" + updateItem.getName());
            return ResponseEntity.ok().body(updateItem);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping("/sort")
    public ResponseEntity<?> sortedItemsByPrice(@RequestParam Double startPrice, @RequestParam Double finalPrice) {
        try {
            List<Item> items = itemService.sortItemByPrice(startPrice, finalPrice);
            return ResponseEntity.ok().body(items);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}