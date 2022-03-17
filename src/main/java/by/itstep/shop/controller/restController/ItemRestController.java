package by.itstep.shop.controller.restController;

import by.itstep.shop.dao.model.Item;
import by.itstep.shop.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/item")
public class ItemRestController {


    private final Logger logger = LoggerFactory.getLogger(ItemRestController.class);

    private final ItemService itemService;


    public ItemRestController(ItemService itemService) {
        this.itemService = itemService;
    }


    @GetMapping("/")
    public ResponseEntity<?> item(@RequestParam Long id) {
        try {
            return ResponseEntity.ok().body(itemService.findItemById(id));
        } catch (Exception e) {
            logger.info(e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }
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
            logger.error(e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }



    @PostMapping("/update")
    @PreAuthorize(value = "hasAuthority('item:write')")
    public ResponseEntity<?> updateItem(@RequestParam Long id, @RequestBody Item item) {
        try {
            Item updateItem = itemService.updateItem(itemService.findItemById(id), item);
            logger.info("item update:" + updateItem.getName());
            return ResponseEntity.ok().body(updateItem);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping("/sort")
    public ResponseEntity<?> sortedItemsByPrice(@RequestParam Double startPrice, @RequestParam Double finalPrice) {
        try {
            List<Item> items = itemService.sortItemByPrice(startPrice, finalPrice);
            return ResponseEntity.ok().body(items);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}