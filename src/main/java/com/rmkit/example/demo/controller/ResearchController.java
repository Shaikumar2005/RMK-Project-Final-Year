package com.rmkit.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ResearchController {

    @GetMapping("/research")
    public String research(Model model) {
        // Optional: pass a username if you want a greeting; safe default is used in the view
        // model.addAttribute("username", "User");
        return "research"; // resolves to templates/research.html
    }
}