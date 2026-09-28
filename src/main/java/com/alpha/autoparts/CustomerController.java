package com.alpha.autoparts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CustomerController {

    @Autowired
    private CustomerRepository repo;

    @GetMapping("/customers")
    public String showCustomers(Model model) {
        model.addAttribute("listCustomers", repo.findAll());
        return "customers"; 
    }

    @GetMapping("/customers/new")
    public String showNewForm(Model model) {
        model.addAttribute("customer", new Customer());
        model.addAttribute("pageTitle", "Add New Customer");
        return "customer_form";
    }

    @PostMapping("/customers/save")
    public String saveCustomer(Customer customer) {
        repo.save(customer);
        return "redirect:/customers";
    }

    @GetMapping("/customers/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        Customer customer = repo.findById(id).get();
        model.addAttribute("customer", customer);
        model.addAttribute("pageTitle", "Edit Customer (ID: " + id + ")");
        return "customer_form";
    }

    @GetMapping("/customers/delete/{id}")
    public String deleteCustomer(@PathVariable Integer id) {
        repo.deleteById(id);
        return "redirect:/customers";
    }
}