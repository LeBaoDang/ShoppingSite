package com.store.repository;

import com.store.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category,String> {

    // phan trang cho category
//    Page<Product> findCategoryProduct(Long id, Pageable pageable);

}
