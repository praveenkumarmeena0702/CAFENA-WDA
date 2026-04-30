package com.cafena.controller;

import com.cafena.model.CartItem;
import com.cafena.model.CartRequest;
import com.cafena.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    /** GET /api/cart/{userId} — fetch all cart items for a user */
    @GetMapping("/{userId}")
    public ResponseEntity<List<CartItem>> getCart(@PathVariable Long userId) {
        return ResponseEntity.ok(cartService.getCartByUser(userId));
    }

    /** POST /api/cart/add — add item or increment qty */
    @PostMapping("/add")
    public ResponseEntity<CartItem> addItem(@RequestBody CartRequest req) {
        return ResponseEntity.ok(cartService.addOrUpdateItem(req));
    }

    /** PUT /api/cart/{itemId}/quantity — update quantity of specific item */
    @PutMapping("/{itemId}/quantity")
    public ResponseEntity<?> updateQty(
            @PathVariable Long itemId,
            @RequestBody Map<String, Integer> body) {
        int qty = body.getOrDefault("quantity", 0);
        CartItem updated = cartService.updateQuantity(itemId, qty);
        if (updated == null) return ResponseEntity.ok(Map.of("message", "Item removed"));
        return ResponseEntity.ok(updated);
    }

    /** DELETE /api/cart/{itemId} — remove a specific item */
    @DeleteMapping("/{itemId}")
    public ResponseEntity<?> removeItem(@PathVariable Long itemId) {
        cartService.removeItem(itemId);
        return ResponseEntity.ok(Map.of("message", "Item removed"));
    }

    /** DELETE /api/cart/clear/{userId} — clear entire cart for a user */
    @DeleteMapping("/clear/{userId}")
    public ResponseEntity<?> clearCart(@PathVariable Long userId) {
        cartService.clearCart(userId);
        return ResponseEntity.ok(Map.of("message", "Cart cleared"));
    }
}
