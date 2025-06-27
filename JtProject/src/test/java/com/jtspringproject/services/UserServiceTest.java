package com.jtspringproject.services;

import com.jtspringproject.dao.CategoryDao;
import com.jtspringproject.models.Category;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryServiceTest {

    @Test
    void testGetCategoriesReturnsList() {
        CategoryDao categoryDao = mock(CategoryDao.class);
        when(categoryDao.getCategories()).thenReturn(Collections.emptyList());

        CategoryService categoryService = new CategoryService(categoryDao);

        assertNotNull(categoryService.getCategories());
        assertEquals(0, categoryService.getCategories().size());
    }

    @Test
    void testAddCategoryDelegatesToDao() {
        CategoryDao categoryDao = mock(CategoryDao.class);
        CategoryService categoryService = new CategoryService(categoryDao);
        Category category = new Category();
        when(categoryDao.addCategory(anyString())).thenReturn(category);

        Category result = categoryService.addCategory("test");
        assertSame(category, result);
        verify(categoryDao).addCategory("test");
    }
} 