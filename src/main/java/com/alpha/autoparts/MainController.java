package com.alpha.autoparts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;

    // صفحة الدخول
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    // معالجة تسجيل الدخول
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model, HttpSession session) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            // التحقق من كلمة المرور
            if (user.getPasswordHash() != null && user.getPasswordHash().equals(password)) {
                // حفظ بيانات المستخدم في الجلسة
                session.setAttribute("loggedInUser", user);
                return "redirect:/dashboard";
            }
        }
        
        // في حال الخطأ
        model.addAttribute("error", "اسم المستخدم أو كلمة المرور غير صحيحة!");
        return "login";
    }

    // تسجيل الخروج
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    // لوحة التحكم (الأزرار التسعة)
    @GetMapping("/dashboard")
    public String dashboardPage(HttpSession session) {
        // حماية صفحة لوحة التحكم
        if (session.getAttribute("loggedInUser") == null) {
            return "redirect:/login";
        }
        return "dashboard";
    }

    // توجيه تلقائي عند فتح البرنامج
    @GetMapping("/")
    public String index() {
        return "redirect:/login";
    }
}
