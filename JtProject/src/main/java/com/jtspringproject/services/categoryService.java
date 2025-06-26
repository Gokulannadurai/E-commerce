package com.jtspringproject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;

import com.jtspringproject.dao.CategoryDao;
import com.jtspringproject.models.Category;

/**
 * Service layer for handling business logic related to categories.
 */
@Service
public class CategoryService implements ICategoryService {
	
	private final CategoryDao categoryDao;
	
	@Autowired
	public CategoryService(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
	
	public Category addCategory(String name) {
		return this.categoryDao.addCategory(name);
	}
	
	@Cacheable("categories")
	public List<Category> getCategories(){
		return this.categoryDao.getCategories();
	}
	
	public Boolean deleteCategory(int id) {
		return this.categoryDao.deletCategory(id);
	}
	
	public Category updateCategory(int id,String name) {
		return this.categoryDao.updateCategory(id, name);
	}

	public Category getCategory(int id) {
		return this.categoryDao.getCategory(id);
	}
}
