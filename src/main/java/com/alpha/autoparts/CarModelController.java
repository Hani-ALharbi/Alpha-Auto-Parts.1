package com.alpha.autoparts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CarModelController {

    @Autowired
    private CarModelRepository repo;

    @Autowired
    private BrandRepository brandRepo; // عشان القائمة المنسدلة

    @GetMapping("/car-models")
    public String showCarModels(Model model) {
        model.addAttribute("listCarModels", repo.findAll());
        return "car_models"; 
    }

    @GetMapping("/car-models/new")
    public String showNewForm(Model model) {
        model.addAttribute("carModel", new CarModel());
        model.addAttribute("listBrands", brandRepo.findAll()); // نرسل الماركات للواجهة
        model.addAttribute("pageTitle", "Add New Car Model");
        return "car_model_form";
    }

    @PostMapping("/car-models/save")
    public String saveCarModel(CarModel carModel) {
        repo.save(carModel);
        return "redirect:/car-models";
    }

    @GetMapping("/car-models/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        CarModel carModel = repo.findById(id).get();
        model.addAttribute("carModel", carModel);
        model.addAttribute("listBrands", brandRepo.findAll()); // نرسل الماركات في التعديل
        model.addAttribute("pageTitle", "Edit Car Model (ID: " + id + ")");
        return "car_model_form";
    }

    @GetMapping("/car-models/delete/{id}")
    public String deleteCarModel(@PathVariable Integer id) {
        repo.deleteById(id);
        return "redirect:/car-models";
    }
}