package com.cafena.service;

import com.cafena.model.CartItem;
import com.cafena.model.CartRequest;
import com.cafena.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartItemRepository cartItemRepository;

    public List<CartItem> getCartByUser(Long userId) {
        return cartItemRepository.findByUserId(userId);
    }

    public CartItem addOrUpdateItem(CartRequest req) {
        Optional<CartItem> existing =
                cartItemRepository.findByUserIdAndItemName(req.getUserId(), req.getItemName());

        if (existing.isPresent()) {
            CartItem item = existing.get();
            item.setQuantity(item.getQuantity() + req.getQuantity());
            return cartItemRepository.save(item);
        }

        CartItem newItem = CartItem.builder()
                .userId(req.getUserId())
                .itemName(req.getItemName())
                .price(req.getPrice())
                .quantity(req.getQuantity())
                .imgUrl(req.getImgUrl())
                .build();

        return cartItemRepository.save(newItem);
    }

    public CartItem updateQuantity(Long itemId, int quantity) {
        CartItem item = cartItemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Cart item not found: " + itemId));

        if (quantity <= 0) {
            cartItemRepository.delete(item);
            return null;
        }
        item.setQuantity(quantity);
        return cartItemRepository.save(item);
    }

    public void removeItem(Long itemId) {
        cartItemRepository.deleteById(itemId);
    }

    @Transactional
    public void clearCart(Long userId) {
        cartItemRepository.deleteByUserId(userId);
    }
}
