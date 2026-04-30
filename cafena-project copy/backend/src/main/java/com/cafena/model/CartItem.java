package com.cafena.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cart_items")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String itemName;

    @Column(nullable = false)
    private String price;

    @Column(nullable = false)
    private int quantity;

    private String imgUrl;

    public CartItem() {}

    public CartItem(Long id, Long userId, String itemName, String price, int quantity, String imgUrl) {
        this.id = id;
        this.userId = userId;
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
        this.imgUrl = imgUrl;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }
    public String getPrice() { return price; }
    public void setPrice(String price) { this.price = price; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public String getImgUrl() { return imgUrl; }
    public void setImgUrl(String imgUrl) { this.imgUrl = imgUrl; }

    public static CartItemBuilder builder() {
        return new CartItemBuilder();
    }

    public static class CartItemBuilder {
        private Long id;
        private Long userId;
        private String itemName;
        private String price;
        private int quantity;
        private String imgUrl;

        public CartItemBuilder id(Long id) { this.id = id; return this; }
        public CartItemBuilder userId(Long userId) { this.userId = userId; return this; }
        public CartItemBuilder itemName(String itemName) { this.itemName = itemName; return this; }
        public CartItemBuilder price(String price) { this.price = price; return this; }
        public CartItemBuilder quantity(int quantity) { this.quantity = quantity; return this; }
        public CartItemBuilder imgUrl(String imgUrl) { this.imgUrl = imgUrl; return this; }

        public CartItem build() {
            return new CartItem(id, userId, itemName, price, quantity, imgUrl);
        }
    }
}
