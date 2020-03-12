package com.shipping.program.controller;

import com.shipping.program.model.Item;
import com.shipping.program.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ItemController
{
    private ItemRepository itemRepository;

    @Autowired
    public ItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping("/item/{title}/seller/{sellerName}/cat/{categoryId}/price/{price}")
    public ResponseEntity<Boolean> isItemQualified(
        @PathVariable String title,
        @PathVariable String sellerName,
        @PathVariable long categoryId,
        @PathVariable double price)
    {
        final List<Item> qualifiedItems =
                itemRepository.findItemByQualification(title, price, sellerName, categoryId);
        if (qualifiedItems.isEmpty()) {
            return ResponseEntity.ok(false);
        }
        return ResponseEntity.ok(true);
    }
}
