package com.store.service;

import com.store.entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

	public List<Product> findAllProduct();

	public Product getOneProduct(Long id);

    // phan trang
	public Page<Product> findAllProduct(int pageSize, int pageNumber) throws Exception;

    // phan trang product category
	public Page<Product> findCategoryProduct(String categoryId, int pageSize, int pageNumber) throws Exception;

	// phan trang search
	public Page<Product> findSearch(String name, int pageSize, int pageNumber) throws Exception;

	public Product createProduct(Product product);
	
	public Product updateProduct(Product product);
	
	public void deleteProduct(Long id);
}
