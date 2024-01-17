package com.springbootdemo.controller;


import com.springbootdemo.model.Product;
import com.springbootdemo.repository.CategoryRepository;
import com.springbootdemo.repository.ProductRepository;
import com.springbootdemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ProductService productService;

    @GetMapping
    public String showList(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        Page<Product> productPage = productService.getAllProduct(page,size);
        model.addAttribute("page", productPage);
        return "product/list";
    }

    @GetMapping("/create")
    public ModelAndView createForm() {
        ModelAndView modelAndView = new ModelAndView("product/create");
        modelAndView.addObject("categorys", categoryRepository.findAll());
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }

    @PostMapping("/save")
    public ModelAndView create(Product product) {
        ModelAndView modelAndView = new ModelAndView("redirect:/products");
        productRepository.save(product);
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editForm(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("product/edit");
        modelAndView.addObject("categorys", categoryRepository.findAll());
        modelAndView.addObject("product", productRepository.findById(id).get());
        return modelAndView;
    }

    @PostMapping("/update")
    public ModelAndView edit(Product product) {
        productRepository.save(product);
        return new ModelAndView("redirect:/products");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable Long id) {
        productRepository.deleteById(id);
        return new ModelAndView("redirect:/products");
    }

    @GetMapping("/search")
    public ModelAndView showListSearch(@RequestParam String search, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        ModelAndView modelAndView =new ModelAndView("product/list");
        modelAndView.addObject("page", productService.getAllSearchByName(page,size,search));
        return modelAndView;
    }
    @GetMapping("/searchByPrice")
    public ModelAndView showListSearchByPrice(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size, Double start, Double end) {
        ModelAndView modelAndView =new ModelAndView("product/list");

        modelAndView.addObject("page", productService.getAllPriceBetween(page,size,start,end));
        return modelAndView;
    }
    @GetMapping("/search-soft")
    public ModelAndView showListSortByPrice(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        ModelAndView modelAndView =new ModelAndView("product/list");

        modelAndView.addObject("page", productService.getAllSort(page,size));
        return modelAndView;
    }
    @GetMapping("/search-soft-desc")
    public ModelAndView showListSortByPriceDESC(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        ModelAndView modelAndView =new ModelAndView("product/list");
        modelAndView.addObject("page", productService.getAllSortDesc(page,size));
        return modelAndView;
    }
}
