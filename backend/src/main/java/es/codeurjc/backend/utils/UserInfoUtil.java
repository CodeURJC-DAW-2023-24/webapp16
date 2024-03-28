package es.codeurjc.backend.utils;

import es.codeurjc.backend.model.User;
import es.codeurjc.backend.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.security.Principal;

@Component
public class UserInfoUtil {

    @Autowired
    private UserRepository userRepository;

    public void addUserInfoToModel(Model model, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        if (principal != null) {
            String name_session = principal.getName();
            User user = userRepository.findByName(name_session).orElseThrow();
            model.addAttribute("username", user.getName());
            if (request.isUserInRole("ADMIN")) {
                model.addAttribute("admin", request.isUserInRole("ADMIN"));
                model.addAttribute("user", request.isUserInRole("USER"));
            } else {
                model.addAttribute("user", request.isUserInRole("USER"));
            }
        }
    }
}