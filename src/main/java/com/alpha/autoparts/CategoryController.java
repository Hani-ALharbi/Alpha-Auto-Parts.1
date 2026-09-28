package com.alpha.autoparts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CategoryController {

    @Autowired
    private CategoryRepository repo;

    @GetMapping("/categories")
    public String showCategories(Model model) {
        model.addAttribute("listCategories", repo.findAll());
        return "categories"; 
    }

    @GetMapping("/categories/new")
    public String showNewForm(Model model) {
        model.addAttribute("category", new Category());
        model.addAttribute("pageTitle", "Add New Category");
        return "category_form";
    }

    @PostMapping("/categories/save")
    public String saveCategory(Category category) {
        repo.save(category);
        return "redirect:/categories";
    }

    @GetMapping("/categories/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        Category category = repo.findById(id).get();
        model.addAttribute("category", category);
        model.addAttribute("pageTitle", "Edit Category (ID: " + id + ")");
        return "category_form";
    }

    @GetMapping("/categories/delete/{id}")
    public String deleteCategory(@PathVariable Integer id) {
        repo.deleteById(id);
        return "redirect:/categories";
    }
}