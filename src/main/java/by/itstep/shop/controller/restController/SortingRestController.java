package by.itstep.shop.controller.restController;

import by.itstep.shop.dao.model.Item;
import by.itstep.shop.service.ItemService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/sort")
public class SortingRestController {
    private final ItemService itemService;

    public SortingRestController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/price")
    public List<Item> sortedItemsByPrice(@RequestBody Double startPrice, @RequestBody Double lastPrice) {
        return itemService.sortItemByPrice(startPrice,lastPrice);
    }
}
