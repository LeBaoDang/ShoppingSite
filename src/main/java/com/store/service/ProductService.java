package com.store.service;

import com.store.entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    List<Product> findAllProduct();

    Product GetOneProduct(Long id);

    // phan trang
    Page<Product> findAllProduct(int pageSize, int pageNumber) throws Exception;

    // phan trang product category
    Page<Product> findCategoryProduct(String categoryId, int pageSize, int pageNumber) throws Exception;

	List<Product> findAll();
}
