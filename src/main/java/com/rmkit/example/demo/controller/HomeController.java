package com.rmkit.example.demo.controller;

import com.rmkit.example.demo.model.User;
import com.rmkit.example.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    private final UserService userService;
    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String rootRedirect() {
        return "redirect:/login";
    }

    // ----- Login -----
    @GetMapping("/login")
    public String loginPage(Model model) {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        Model model) {
        if (userService.validateLogin(username, password)) {
            model.addAttribute("username", username);
            return "access"; // success page
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }

    // ----- Register -----
    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String doRegister(@Valid @ModelAttribute("user") User user,
                             BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            return "register";
        }

        try {
            userService.register(user);
        } catch (IllegalArgumentException ex) {
            String msg = ex.getMessage().toLowerCase();
            if (msg.contains("username")) {
                bindingResult.rejectValue("username", "exists", ex.getMessage());
            } else if (msg.contains("email")) {
                bindingResult.rejectValue("email", "exists", ex.getMessage());
            } else {
                model.addAttribute("formError", ex.getMessage());
            }
            return "register";
        }

        model.addAttribute("msg", "Registration successful. Please log in.");
        return "login";
    }
}