package org.tictactoe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {

        if (error != null) {
            model.addAttribute("errorMsg", "Your username and password are invalid.");
        } else {
            model.addAttribute("errorMsg", null);
        }
        if (logout != null) {
            model.addAttribute("msg", "You have been logged out successfully.");
        }

        return "login";
    }
}
