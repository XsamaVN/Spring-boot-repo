package com.springbootdemo.repository;

import com.springbootdemo.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Product p WHERE p.category.id = ?1")
    void deleteProductsByCategoryId(Long categoryId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Category c WHERE c.id = ?1")
    void deleteCategoryById(Long categoryId);
}
