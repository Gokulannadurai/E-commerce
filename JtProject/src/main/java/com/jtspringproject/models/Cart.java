package com.jtspringproject.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Model class for the Shopping Cart.
 */
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private User customer;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartProduct> products = new ArrayList<>();


    public Cart() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public List<CartProduct> getProducts() {
        return products;
    }

    public void setProducts(List<CartProduct> products) {
        this.products = products;
    }

    public void addProduct(CartProduct product) {
        products.add(product);
        product.setCart(this);
    }

    public void removeProduct(CartProduct product) {
        products.remove(product);
        product.setCart(null);
    }
}
