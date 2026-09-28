package com.alpha.autoparts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ReportController {

    @Autowired private OrderRepository orderRepo;
    @Autowired private SparePartRepository partRepo;
    @Autowired private UserRepository userRepo;
    @Autowired private CustomerRepository customerRepo;
    @Autowired private SupplierRepository supplierRepo;
    @Autowired private CategoryRepository categoryRepo;
    @Autowired private BrandRepository brandRepo;
    @Autowired private CarModelRepository carModelRepo;
    @Autowired private UnitPriceRepository priceRepo;

    // الواجهة الرئيسية لمركز التقارير
    @GetMapping("/reports")
    public String showReportsHub(Model model) {
        return "reports"; 
    }

    // 1. تقرير المبيعات والطلبات
    @GetMapping("/reports/orders")
    public String reportOrders(Model model) {
        List<OrderRecord> orders = orderRepo.findAll();
        
        List<UnitPrice> priceList = priceRepo.findAll();
        java.util.Map<Integer, Integer> pricesMap = new java.util.HashMap<>();
        for (UnitPrice p : priceList) {
            pricesMap.put(p.getUnitPriceID(), p.getPrice());
        }
        model.addAttribute("pricesMap", pricesMap);

        double totalRevenue = 0;
        int completedOrders = 0;
        int pendingOrders = 0;

        for (OrderRecord order : orders) {
            if (order.getUnitPriceID() != null && pricesMap.containsKey(order.getUnitPriceID())) {
                Integer price = pricesMap.get(order.getUnitPriceID());
                if (price != null) {
                    totalRevenue += price;
                }
            }
            if (order.getStatus() != null && order.getStatus()) {
                completedOrders++;
            } else {
                pendingOrders++;
            }
        }
        model.addAttribute("totalRevenue", totalRevenue);
        model.addAttribute("totalRevenueStr", String.format(java.util.Locale.ENGLISH, "%,.2f", totalRevenue));
        model.addAttribute("totalOrders", orders.size());
        model.addAttribute("completedOrders", completedOrders);
        model.addAttribute("pendingOrders", pendingOrders);
        
        List<SparePart> parts = partRepo.findAll();
        java.util.Map<Integer, String> partNames = new java.util.HashMap<>();
        for (SparePart p : parts) {
            partNames.put(p.getPartID(), p.getPartName());
        }
        model.addAttribute("partNames", partNames);
        
        List<Customer> customers = customerRepo.findAll();
        java.util.Map<Integer, String> customerNames = new java.util.HashMap<>();
        for (Customer c : customers) {
            customerNames.put(c.getCustomerID(), c.getFullName());
        }
        model.addAttribute("customerNames", customerNames);
        
        // --- Calculate Best-Selling Parts ---
        java.util.Map<Integer, Integer> partSalesCount = new java.util.HashMap<>();
        for (OrderRecord order : orders) {
            if (order.getStatus() != null && order.getStatus() && order.getPartID() != null) {
                partSalesCount.put(order.getPartID(), partSalesCount.getOrDefault(order.getPartID(), 0) + 1);
            }
        }
        List<java.util.Map.Entry<Integer, Integer>> sortedParts = new java.util.ArrayList<>(partSalesCount.entrySet());
        sortedParts.sort((a, b) -> b.getValue().compareTo(a.getValue()));
        List<java.util.Map<String, Object>> bestSellingParts = new java.util.ArrayList<>();
        int count = 0;
        for (java.util.Map.Entry<Integer, Integer> entry : sortedParts) {
            if (count >= 5) break;
            java.util.Map<String, Object> map = new java.util.HashMap<>();
            map.put("partName", partNames.getOrDefault(entry.getKey(), "Unknown Part"));
            map.put("salesCount", entry.getValue());
            bestSellingParts.add(map);
            count++;
        }
        model.addAttribute("bestSellingParts", bestSellingParts);
        // ------------------------------------
        
        model.addAttribute("listOrders", orders);
        return "report-orders"; 
    }

    // 2. تقرير المخزون
    @GetMapping("/reports/parts")
    public String reportParts(Model model) {
        List<SparePart> parts = partRepo.findAll();
        int inStock = 0;
        int lowStock = 0;
        for (SparePart part : parts) {
            if (part.getStockQuantity() != null && part.getReorderLevel() != null && part.getStockQuantity() <= part.getReorderLevel()) {
                lowStock++;
            } else {
                inStock++;
            }
        }
        
        List<Supplier> suppliers = supplierRepo.findAll();
        java.util.Map<Integer, String> supplierNames = new java.util.HashMap<>();
        for (Supplier s : suppliers) {
            supplierNames.put(s.getSupplierID(), s.getName());
        }
        
        List<Category> categories = categoryRepo.findAll();
        java.util.Map<Integer, String> categoryNames = new java.util.HashMap<>();
        for (Category c : categories) {
            categoryNames.put(c.getCategoryID(), c.getCategoryName());
        }
        
        model.addAttribute("supplierNames", supplierNames);
        model.addAttribute("categoryNames", categoryNames);
        
        model.addAttribute("totalParts", parts.size());
        model.addAttribute("inStock", inStock);
        model.addAttribute("lowStock", lowStock);
        model.addAttribute("listParts", parts);
        return "report-parts";
    }

    // 3. تقرير الموظفين
    @GetMapping("/reports/users")
    public String reportUsers(Model model) {
        List<User> users = userRepo.findAll();
        long admins = users.stream().filter(u -> "Admin".equalsIgnoreCase(u.getTypeUser())).count();
        long staff = users.size() - admins;
        model.addAttribute("totalUsers", users.size());
        model.addAttribute("admins", admins);
        model.addAttribute("staff", staff);
        model.addAttribute("listUsers", users);
        return "report-users";
    }

    // 4. تقرير العملاء
    @GetMapping("/reports/customers")
    public String reportCustomers(Model model) {
        List<Customer> customers = customerRepo.findAll();
        long individuals = 0;
        long workshops = 0;
        for (Customer c : customers) {
            if ("ورشة".equals(c.getType()) || "Workshop".equalsIgnoreCase(c.getType())) {
                workshops++;
            } else {
                individuals++;
            }
        }
        model.addAttribute("totalCustomers", customers.size());
        model.addAttribute("individuals", individuals);
        model.addAttribute("workshops", workshops);
        model.addAttribute("listCustomers", customers);
        return "report-customers";
    }

    // 5. تقرير الموردين
    @GetMapping("/reports/suppliers")
    public String reportSuppliers(Model model) {
        List<Supplier> suppliers = supplierRepo.findAll();
        model.addAttribute("totalSuppliers", suppliers.size());
        model.addAttribute("listSuppliers", suppliers);
        return "report-suppliers";
    }

    // 6. تقرير التصنيفات
    @GetMapping("/reports/categories")
    public String reportCategories(Model model) {
        List<Category> categories = categoryRepo.findAll();
        model.addAttribute("totalCategories", categories.size());
        model.addAttribute("listCategories", categories);
        return "report-categories";
    }

    // 7. تقرير العلامات التجارية
    @GetMapping("/reports/brands")
    public String reportBrands(Model model) {
        List<Brand> brands = brandRepo.findAll();
        model.addAttribute("totalBrands", brands.size());
        model.addAttribute("listBrands", brands);
        return "report-brands";
    }

    // 8. تقرير موديلات السيارات
    @GetMapping("/reports/carmodels")
    public String reportCarModels(Model model) {
        List<CarModel> carModels = carModelRepo.findAll();
        
        List<Brand> brands = brandRepo.findAll();
        java.util.Map<Integer, String> brandNames = new java.util.HashMap<>();
        for (Brand b : brands) {
            brandNames.put(b.getBrandID(), b.getNameBrand());
        }
        model.addAttribute("brandNames", brandNames);
        
        model.addAttribute("totalCarModels", carModels.size());
        model.addAttribute("listCarModels", carModels);
        return "report-carmodels";
    }

    // 9. تقرير الأسعار
    @GetMapping("/reports/prices")
    public String reportPrices(Model model) {
        List<UnitPrice> prices = priceRepo.findAll();
        model.addAttribute("totalPrices", prices.size());
        model.addAttribute("listPrices", prices);
        return "report-prices";
    }
}