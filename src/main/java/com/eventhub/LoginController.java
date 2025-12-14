package com.eventhub;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private final UserRepository userRepository;

    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String redirectHomeToLogin() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam Long userId, HttpSession session, Model model) {
        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            model.addAttribute("error", "User ID not found! Try 1, 2, or 3.");
            return "login";
        }

        session.setAttribute("currentUser", user);

        return "redirect:/events";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}