package com.jtspringproject.services;

import com.jtspringproject.models.Category;
import java.util.List;

public interface ICategoryService {
    Category addCategory(String name);
    List<Category> getCategories();
    Boolean deleteCategory(int id);
    Category updateCategory(int id, String name);
    Category getCategory(int id);
} 