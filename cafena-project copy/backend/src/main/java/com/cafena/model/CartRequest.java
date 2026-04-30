package com.cafena.model;

public class CartRequest {
    private Long userId;
    private String itemName;
    private String price;
    private int quantity;
    private String imgUrl;

    public CartRequest() {}

    public CartRequest(Long userId, String itemName, String price, int quantity, String imgUrl) {
        this.userId = userId;
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
        this.imgUrl = imgUrl;
    }

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
}
