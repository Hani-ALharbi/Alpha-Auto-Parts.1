package com.alpha.autoparts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepo;

    // 1. عرض صفحة الجدول
    @GetMapping("/users")
    public String listUsers(Model model) {
        model.addAttribute("listUsers", userRepo.findAll());
        return "users-list"; // اسم صفحة الـ HTML
    }

    // 2. فتح صفحة الإضافة (صفحة جديدة)
    @GetMapping("/users/new")
    public String showAddForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle", "Add New User");
        return "user-form"; // اسم صفحة فورم الإضافة
    }

    // 3. حفظ المستخدم والرجوع للجدول
    @PostMapping("/users/save")
    public String saveUser(@ModelAttribute("user") User user) {
        userRepo.save(user);
        return "redirect:/users";
    }
    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Integer id) {
    	userRepo.deleteById(id);
        return "redirect:/users";
    }

    // 4. فتح صفحة التعديل
    @GetMapping("/users/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("user", user);
        model.addAttribute("pageTitle", "Edit User");
        return "user-form";
    }
}

