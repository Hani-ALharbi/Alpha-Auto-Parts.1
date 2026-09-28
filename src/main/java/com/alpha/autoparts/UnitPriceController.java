package com.alpha.autoparts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UnitPriceController {

    // هنا كان النقص! استدعاء مستودع الأسعار عشان يشتغل الـ repo
    @Autowired
    private UnitPriceRepository repo;

    // دالة عرض الجدول (كانت ناقصة في الكود اللي أرسلته)
    @GetMapping("/unit-prices")
    public String showUnitPrices(Model model) {
        model.addAttribute("listUnitPrices", repo.findAll());
        return "unit_prices"; 
    }

    @GetMapping("/unit-prices/new")
    public String showNewForm(Model model) {
        model.addAttribute("unitPrice", new UnitPrice());
        model.addAttribute("pageTitle", "Add New Unit Price");
        return "unit_price_form";
    }

    @PostMapping("/unit-prices/save")
    public String saveUnitPrice(UnitPrice unitPrice) {
        repo.save(unitPrice);
        return "redirect:/unit-prices";
    }

    @GetMapping("/unit-prices/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        UnitPrice unitPrice = repo.findById(id).get();
        model.addAttribute("unitPrice", unitPrice);
        model.addAttribute("pageTitle", "Edit Unit Price (ID: " + id + ")");
        return "unit_price_form";
    }

    @GetMapping("/unit-prices/delete/{id}")
    public String deleteUnitPrice(@PathVariable Integer id) {
        repo.deleteById(id);
        return "redirect:/unit-prices";
    }
}