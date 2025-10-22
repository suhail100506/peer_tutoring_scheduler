package com.example.peertutoring.controller;

import com.example.peertutoring.entity.Admin;
import com.example.peertutoring.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping
    public String redirectToDashboard() {
        return "redirect:/admin/dashboard";
    }
    
    @GetMapping("/login")
    public String showLoginForm() {
        return "admin/login";
    }
    
    @PostMapping("/login")
    public String processLogin(@RequestParam String username, 
                             @RequestParam String password) {
        // Authentication logic will be handled by Spring Security
        return "redirect:/admin/dashboard";
    }
    
    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        // Add any dashboard data to the model
        return "admin/dashboard";
    }
    
    @GetMapping("/profile")
    public String showProfile(Model model) {
        // Get current admin from security context
        // For now, we'll just return the profile page
        return "admin/profile";
    }
    
    @GetMapping("/settings")
    public String showSettings(Model model) {
        // Add settings data to the model
        return "admin/settings";
    }
    
    @GetMapping("/admins")
    public String listAdmins(Model model) {
        model.addAttribute("admins", adminService.getAllAdmins());
        return "admin/list";
    }
    
    @GetMapping("/admins/new")
    public String showAdminForm(Model model) {
        model.addAttribute("admin", new Admin());
        return "admin/form";
    }
    
    @PostMapping("/admins")
    public String saveAdmin(@ModelAttribute("admin") Admin admin) {
        adminService.saveAdmin(admin);
        return "redirect:/admin/admins";
    }
    
    @GetMapping("/admins/edit/{id}")
    public String showEditAdminForm(@PathVariable Long id, Model model) {
        model.addAttribute("admin", adminService.getAdminById(id));
        return "admin/form";
    }
    
    @GetMapping("/admins/delete/{id}")
    public String deleteAdmin(@PathVariable Long id) {
        adminService.deleteAdmin(id);
        return "redirect:/admin/admins";
    }
}
