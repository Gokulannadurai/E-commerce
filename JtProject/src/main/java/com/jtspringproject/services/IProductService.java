package com.jtspringproject.services;

import com.jtspringproject.models.Product;
import java.util.List;

public interface IProductService {
    List<Product> getProducts();
    Product addProduct(Product product);
    Product getProduct(int id);
    Product updateProduct(int id, Product product);
    boolean deleteProduct(int id);
} 