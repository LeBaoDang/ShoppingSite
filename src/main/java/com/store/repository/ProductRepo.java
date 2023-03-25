package com.store.repository;

import com.store.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    // phan trang
    Page<Product> findAll(Pageable pageable);

    // phan trang cho category
    //@Query(value = "SELECT * FROM Products WHERE CategoryId = ?1", nativeQuery = true )
    @Query(" SELECT p FROM Product p WHERE p.category.id = ?1")
    Page<Product> findCategoryProduct(String categoryId, Pageable pageable);

    @Query(value = "select * from Products where name like ?1 ", nativeQuery = true)
    Page<Product> findBySearchProduct(String nameProduct, Pageable pageable);

}
