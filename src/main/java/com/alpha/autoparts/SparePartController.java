package com.alpha.autoparts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SparePartController {

    @Autowired
    private SparePartRepository repo;

    // --- ربط باقي الجداول عشان نعبي القوائم المنسدلة (Dropdowns) في الواجهة ---
    // (ملاحظة: تحتاج تنشئ لهم ملفات Repository بنفس طريقة SupplierRepository إذا ما أنشأتها بعد)
    @Autowired private CategoryRepository categoryRepo;
    @Autowired private BrandRepository brandRepo;
    @Autowired private CarModelRepository carModelRepo;
    @Autowired private SupplierRepository supplierRepo;
    @Autowired private UnitPriceRepository unitPriceRepo;

    // 1. صفحة العرض (الجدول)
    @GetMapping("/spare-parts")
    public String showSpareParts(Model model) {
        model.addAttribute("listSpareParts", repo.findAll());
        return "spare_parts"; 
    }

    // 2. صفحة الإضافة (النموذج الفارغ)
    @GetMapping("/spare-parts/new")
    public String showNewForm(Model model) {
        model.addAttribute("sparePart", new SparePart());
        model.addAttribute("pageTitle", "Add New Spare Part");
        
        // إرسال قوائم البيانات للواجهة عشان تظهر في الـ Select
        model.addAttribute("listCategories", categoryRepo.findAll());
        model.addAttribute("listBrands", brandRepo.findAll());
        model.addAttribute("listCarModels", carModelRepo.findAll());
        model.addAttribute("listSuppliers", supplierRepo.findAll());
        model.addAttribute("listUnitPrices", unitPriceRepo.findAll());
        
        return "spare_part_form";
    }

    // 3. دالة الحفظ (للإضافة والتعديل)
    @PostMapping("/spare-parts/save")
    public String saveSparePart(SparePart sparePart) {
        repo.save(sparePart);
        return "redirect:/spare-parts";
    }

    // 4. صفحة التعديل (تحميل بيانات قطعة موجودة)
    @GetMapping("/spare-parts/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        SparePart sparePart = repo.findById(id).get();
        model.addAttribute("sparePart", sparePart);
        model.addAttribute("pageTitle", "Edit Spare Part (ID: " + id + ")");
        
        // نحتاج نرسل القوائم هنا أيضاً عشان يقدر المستخدم يعدل اختياراته
        model.addAttribute("listCategories", categoryRepo.findAll());
        model.addAttribute("listBrands", brandRepo.findAll());
        model.addAttribute("listCarModels", carModelRepo.findAll());
        model.addAttribute("listSuppliers", supplierRepo.findAll());
        model.addAttribute("listUnitPrices", unitPriceRepo.findAll());
        
        return "spare_part_form";
    }

    // 5. دالة الحذف
    @GetMapping("/spare-parts/delete/{id}")
    public String deleteSparePart(@PathVariable Integer id) {
        repo.deleteById(id);
        return "redirect:/spare-parts";
    }
}