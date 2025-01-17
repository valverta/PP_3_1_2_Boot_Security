package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/")
    public String adminPage(Model model) {
        model.addAttribute("allUsers", userService.getAllUsers());
        return "admin/admin";
    }

    @GetMapping("/update/{id}")
    public String updatePage(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("allRoles", roleService.getAllRoles());
        return "admin/update";
    }

    @PatchMapping("/update-user")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:/admin/";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.removeById(id);
        return "redirect:/admin/";
    }
}
