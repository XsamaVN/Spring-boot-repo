package com.springbootdemo.controller;


import com.springbootdemo.model.Category;
import com.springbootdemo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping
    public ModelAndView showList(){
        ModelAndView modelAndView = new ModelAndView("category/list");
        modelAndView.addObject("list", categoryRepository.findAll());
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView createForm() {
        ModelAndView modelAndView = new ModelAndView("category/create");
        return modelAndView;
    }

    @PostMapping("/save")
    public ModelAndView create(Category category) {
        ModelAndView modelAndView = new ModelAndView("redirect:/categories");
        categoryRepository.save(category);
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editForm(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("category/edit");
        modelAndView.addObject("category", categoryRepository.findById(id).get());

        return modelAndView;
    }

    @PostMapping("/update")
    public ModelAndView edit(Category category) {
        categoryRepository.save(category);
        return new ModelAndView("redirect:/categories");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable Long id) {
       categoryRepository.deleteProductsByCategoryId(id);
       categoryRepository.deleteCategoryById(id);

        return new ModelAndView("redirect:/categories");
    }
}
