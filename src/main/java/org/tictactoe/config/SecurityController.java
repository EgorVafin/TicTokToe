package org.tictactoe.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.tictactoe.entity.User;

@Controller
@RequiredArgsConstructor
public class SecurityController {

    private SecurityUserDetailsService userDetailsService;

    private PasswordEncoder passwordEncoder;

//    @GetMapping("/login")
//    public String login(HttpServletRequest request, HttpSession session) {
//        session.setAttribute(
//                "error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION")
//        );
//        return "login";
//    }

    private String getErrorMessage(HttpServletRequest request, String key) {
        Exception exception = (Exception) request.getSession().getAttribute(key);
        String error = "";
        if (exception instanceof BadCredentialsException) {
            error = "Invalid username and password!";
        } else if (exception instanceof LockedException) {
            error = exception.getMessage();
        } else {
            error = "Invalid username and password!";
        }
        return error;
    }
}
