package com.alpha.autoparts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class OrderController {

    @Autowired
    private OrderRepository orderRepo;

    // استدعاء الجداول المرتبطة (Foreign Keys)
    @Autowired
    private CustomerRepository customerRepo;
    
    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private SparePartRepository partRepo;
    
    @Autowired
    private UnitPriceRepository priceRepo;

    @GetMapping("/orders")
    public String showOrders(Model model) {
        model.addAttribute("listOrders", orderRepo.findAll());
        
        java.util.List<UnitPrice> priceList = priceRepo.findAll();
        java.util.Map<Integer, Integer> pricesMap = new java.util.HashMap<>();
        for (UnitPrice p : priceList) {
            pricesMap.put(p.getUnitPriceID(), p.getPrice());
        }
        model.addAttribute("pricesMap", pricesMap);
        
        return "orders"; 
    }

    @GetMapping("/orders/new")
    public String showNewForm(Model model) {
        model.addAttribute("orderRecord", new OrderRecord());
        
        // إرسال القوائم للواجهة
        model.addAttribute("listCustomers", customerRepo.findAll());
        model.addAttribute("listUsers", userRepo.findAll());
        model.addAttribute("listParts", partRepo.findAll());
        model.addAttribute("listPrices", priceRepo.findAll());
        
        // خريطة لربط القطعة بالسعر لاستخدامها في الجافاسكريبت
        java.util.Map<Integer, Integer> partPriceMap = new java.util.HashMap<>();
        for (SparePart part : partRepo.findAll()) {
            if (part.getUnitPriceID() != null) {
                partPriceMap.put(part.getPartID(), part.getUnitPriceID());
            }
        }
        model.addAttribute("partPriceMap", partPriceMap);
        
        model.addAttribute("pageTitle", "Create New Order");
        return "order_form";
    }

    @PostMapping("/orders/save")
    public String saveOrder(OrderRecord orderRecord, @RequestParam(value="orderDateStr", required=false) String orderDateStr, org.springframework.web.servlet.mvc.support.RedirectAttributes redirectAttributes) {
        try {
            if (orderDateStr != null && !orderDateStr.isEmpty()) {
                try {
                    orderRecord.setOrderDate(Integer.parseInt(orderDateStr.replace("-", "")));
                } catch (Exception e) {
                    // ignore
                }
            }
            
            if (orderRecord.getPartID() != null) {
                SparePart part = partRepo.findById(orderRecord.getPartID()).orElse(null);
                if (part != null) {
                    // Prevent saving if out of stock
                    if (orderRecord.getOrderID() == null && part.getStockQuantity() != null && part.getStockQuantity() <= 0) {
                        redirectAttributes.addFlashAttribute("errorMessage", "Error: The selected part '" + part.getPartName() + "' is out of stock (نفذت من المخزون).");
                        return "redirect:/orders/new";
                    }
                    
                    orderRecord.setUnitPriceID(part.getUnitPriceID());
                    
                    // Decrease stock for new orders
                    if (orderRecord.getOrderID() == null && part.getStockQuantity() != null && part.getStockQuantity() > 0) {
                        part.setStockQuantity(part.getStockQuantity() - 1);
                        partRepo.save(part);
                    }
                }
            }
            orderRepo.save(orderRecord);
            return "redirect:/orders";
        } catch (Exception e) {
            Throwable rootCause = e;
            while (rootCause.getCause() != null && rootCause != rootCause.getCause()) {
                rootCause = rootCause.getCause();
            }
            redirectAttributes.addFlashAttribute("errorMessage", "Database Error: " + rootCause.getMessage());
            return "redirect:/orders/new";
        }
    }

    @GetMapping("/orders/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        OrderRecord orderRecord = orderRepo.findById(id).get();
        model.addAttribute("orderRecord", orderRecord);
        
        // إرسال القوائم في حالة التعديل أيضاً
        model.addAttribute("listCustomers", customerRepo.findAll());
        model.addAttribute("listUsers", userRepo.findAll());
        model.addAttribute("listParts", partRepo.findAll());
        model.addAttribute("listPrices", priceRepo.findAll());
        
        java.util.Map<Integer, Integer> partPriceMap = new java.util.HashMap<>();
        for (SparePart part : partRepo.findAll()) {
            if (part.getUnitPriceID() != null) {
                partPriceMap.put(part.getPartID(), part.getUnitPriceID());
            }
        }
        model.addAttribute("partPriceMap", partPriceMap);
        
        model.addAttribute("pageTitle", "Edit Order (ID: " + id + ")");
        return "order_form";
    }

    @GetMapping("/orders/delete/{id}")
    public String deleteOrder(@PathVariable Integer id) {
        orderRepo.deleteById(id);
        return "redirect:/orders";
    }
}