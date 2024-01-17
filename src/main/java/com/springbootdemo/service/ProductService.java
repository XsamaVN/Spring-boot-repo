package com.springbootdemo.service;

import com.springbootdemo.model.Product;
import com.springbootdemo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    public Page<Product> getAllProduct(int page, int size){
        Pageable pageable = PageRequest.of(page,size);
        return productRepository.findAll(pageable);
    }

    public Page<Product> getAllSort(int page, int size){
        Pageable pageable = PageRequest.of(page,size);
        return productRepository.findAllByOrderByPrice(pageable);
    }

    public Page<Product> getAllSortDesc(int page, int size){
        Pageable pageable = PageRequest.of(page,size);
        return productRepository.findAllByOrderByPriceDesc(pageable);
    }
    public Page<Product> getAllSearchByName(int page, int size, String input){
        Pageable pageable = PageRequest.of(page,size);
        return productRepository.findByNameContaining(pageable, input);
    }
    public Page<Product> getAllPriceBetween(int page, int size, Double start, Double end ){
        Pageable pageable = PageRequest.of(page,size);
        return productRepository.findByPriceBetween(pageable, start,end);
    }
}
