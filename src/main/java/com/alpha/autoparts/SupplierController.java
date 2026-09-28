package com.alpha.autoparts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
	public class SupplierController {

	    @Autowired
	    private SupplierRepository repo;

	    // 1. صفحة العرض (الجدول)
	    @GetMapping("/suppliers")
	    public String showSuppliers(Model model) {
	        model.addAttribute("listSuppliers", repo.findAll());
	        return "suppliers"; 
	    }

	    // 2. صفحة الإضافة (النموذج الفارغ)
	    @GetMapping("/suppliers/new")
	    public String showNewForm(Model model) {
	        model.addAttribute("supplier", new Supplier());
	        model.addAttribute("pageTitle", "Add New Supplier");
	        return "supplier_form";
	    }

	    // 3. دالة الحفظ (للإضافة والتعديل)
	    @PostMapping("/suppliers/save")
	    public String saveSupplier(Supplier supplier) {
	        repo.save(supplier);
	        return "redirect:/suppliers";
	    }

	    // 4. صفحة التعديل (تحميل بيانات مورد موجود)
	    @GetMapping("/suppliers/edit/{id}")
	    public String showEditForm(@PathVariable("id") Integer id, Model model) {
	        Supplier supplier = repo.findById(id).get();
	        model.addAttribute("supplier", supplier);
	        model.addAttribute("pageTitle", "Edit Supplier (ID: " + id + ")");
	        return "supplier_form";
	    }

	    // 5. دالة الحذف
	    @GetMapping("/suppliers/delete/{id}")
	    public String deleteSupplier(@PathVariable Integer id) {
	        repo.deleteById(id);
	        return "redirect:/suppliers";
	    }
	}

