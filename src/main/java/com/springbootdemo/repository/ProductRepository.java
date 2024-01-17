package com.springbootdemo.repository;


import com.springbootdemo.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByNameContaining(Pageable pageable,String search);

    Page<Product> findByPriceBetween(Pageable pageable,Double start, Double end);

    Page<Product> findAllByOrderByPrice(Pageable pageable);

    Page<Product> findAllByOrderByPriceDesc(Pageable pageable);

    Page<Product> findAll(Pageable pageable);
}
