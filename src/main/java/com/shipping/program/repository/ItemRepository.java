package com.shipping.program.repository;

import com.shipping.program.model.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends CrudRepository<Item, Long> {
    @Query("SELECT i FROM Item i " +
            "JOIN i.seller s " +
            "JOIN i.category c ON c.id = :categoryId " +
            "WHERE i.title = :title " +
            "AND s.name LIKE %:sellerName% " +
            "AND i.price = :price " +
            "AND i.price >= c.minAmt " +
            "AND s.enrolled = true " +
            "AND c.preApproved = true")
    List<Item> findItemByQualification(
        @Param("title") String title,
        @Param("price") double price,
        @Param("sellerName") String sellerName,
        @Param("categoryId") long categoryId);
}
