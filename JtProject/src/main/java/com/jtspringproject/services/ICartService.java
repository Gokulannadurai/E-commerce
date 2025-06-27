package com.jtspringproject.services;

import com.jtspringproject.models.Cart;
import java.util.List;

public interface ICartService {
    Cart addCart(Cart cart);
    List<Cart> getCarts();
    void updateCart(Cart cart);
    void deleteCart(Cart cart);
} 