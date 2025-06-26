package com.jtspringproject.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jtspringproject.models.Product;

/**
 * Data Access Object for Product entities.
 * Handles all database operations related to the Product.
 */
@Repository
public class ProductDao {
	private final SessionFactory sessionFactory;
	
	@Autowired
	public ProductDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	
	@Transactional
	public List<Product> getProducts(){
		return this.sessionFactory.getCurrentSession().createQuery("from Product").list();
	}
	
	@Transactional
	public Product addProduct(Product product) {
		this.sessionFactory.getCurrentSession().save(product);
		return product;
	}
	
	@Transactional
	public Product getProduct(int id) {
		return this.sessionFactory.getCurrentSession().get(Product.class, id);
	}

	@Transactional
	public Product updateProduct(Product product){
		this.sessionFactory.getCurrentSession().update(product);
		return product;
	}
	@Transactional
	public Boolean deletProduct(int id) {

		Session session = this.sessionFactory.getCurrentSession();
		Object persistanceInstance = session.load(Product.class, id);

		if (persistanceInstance != null) {
			session.delete(persistanceInstance);
			return true;
		}
		return false;
	}

	@Transactional
	public List<Product> getProductsWithCategory() {
		return this.sessionFactory.getCurrentSession()
			.createQuery("SELECT p FROM Product p JOIN FETCH p.category", Product.class)
			.list();
	}

}
