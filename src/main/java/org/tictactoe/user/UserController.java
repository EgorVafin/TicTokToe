package org.tictactoe.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.tictactoe.config.SecurityUserDetailsService;
import org.tictactoe.entity.User;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final SecurityUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String register(Model model) {
        UserCreateFormRequest userDto = new UserCreateFormRequest();
        model.addAttribute("user", userDto);
        return "register";
    }

    @PostMapping(value = "/register")
    public String addUser(@ModelAttribute("user") @Valid UserCreateFormRequest userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register";
        }

        User entity = new User();
        entity.setName(userDto.getName());
        entity.setEmail(userDto.getEmail());
        entity.setPassword(passwordEncoder.encode(userDto.getPassword()));

        userDetailsService.createUser(entity);
        return "redirect:/login";
    }
}
