package com.alpha.autoparts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BrandController {

    @Autowired
    private BrandRepository repo;

    @GetMapping("/brands")
    public String showBrands(Model model) {
        model.addAttribute("listBrands", repo.findAll());
        return "brands"; 
    }

    @GetMapping("/brands/new")
    public String showNewForm(Model model) {
        model.addAttribute("brand", new Brand());
        model.addAttribute("pageTitle", "Add New Brand");
        return "brand_form";
    }

    @PostMapping("/brands/save")
    public String saveBrand(Brand brand) {
        repo.save(brand);
        return "redirect:/brands";
    }

    @GetMapping("/brands/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        Brand brand = repo.findById(id).get();
        model.addAttribute("brand", brand);
        model.addAttribute("pageTitle", "Edit Brand (ID: " + id + ")");
        return "brand_form";
    }

    @GetMapping("/brands/delete/{id}")
    public String deleteBrand(@PathVariable Integer id) {
        repo.deleteById(id);
        return "redirect:/brands";
    }
}