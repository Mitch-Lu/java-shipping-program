package com.shipping.program.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Item
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long sellerId;

    private long categoryId;

    public Item(long sellerId, long categoryId, String title, double price) {
        this.sellerId = sellerId;
        this.categoryId = categoryId;
        this.title = title;
        this.price = price;
    }

    @NotNull
    @NotBlank
    private String title;

    private double price;

    @Column(insertable = true, updatable = false)
    private LocalDateTime created;
    private LocalDateTime modified;

    public Item(String title, double price) {
        this.title = title;
        this.price = price;
    }

    @PrePersist
    void onCreate() {
        this.setCreated(LocalDateTime.now());
        this.setModified(LocalDateTime.now());
    }

    @PreUpdate
    void onUpdate() {
        this.setModified(LocalDateTime.now());
    }
}
