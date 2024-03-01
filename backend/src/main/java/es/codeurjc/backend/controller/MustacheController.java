package es.codeurjc.backend.controller;


import es.codeurjc.backend.model.User;
import es.codeurjc.backend.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class MustacheController {
    @Autowired
    UserRepository userRepository;
    @GetMapping("/header")
	public String haed(Model model) {
		return "header";
	}
    @GetMapping("/footer")
	public String foot(Model model) {
		return "footer";
	}
	@GetMapping("/pageBanner")
    public String banner(Model model) {
        return "pageBanner";
    }
	@GetMapping("/login")
    public String login(Model model) {
        return "regLog";
    }


    @GetMapping("/loginerror")
    public String loginerror() {
        return "loginerror";
    }
	@GetMapping("/tournamentCreation")
    public String newTour(Model model) {
        return "tournamentCreate";
    }


	@GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("pageTitle", "About us");
        return "about";
		}
	@GetMapping("/fillMatchReport")
    public String report(Model model) {
        return "fillMatchReport";
	}
	@GetMapping("/profile")
    public String profile(Model model, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        if(principal != null) {
            String name = principal.getName();
            User user = userRepository.findByName(name).orElseThrow();
            model.addAttribute("username", user.getName());
            model.addAttribute("admin", request.isUserInRole("ADMIN"));
            model.addAttribute("user", request.isUserInRole("USER"));

        }
        model.addAttribute("pageTitle", "Profile");

        return "profile";
    }

    @GetMapping("/tournamentBracket")
    public String tournament_racket(Model model) {
        return "tournamentBracket";
	}
	@GetMapping("/playerCreation")
    public String newPlayer(Model model) {
        return "playerCreate";
    }
	@GetMapping("/teamCreation")
    public String addTeam(Model model) {
        model.addAttribute("cup","prueba");
        return "teamCreate";
    }
	@GetMapping("/matchScreen")
    public String showMatchScreen(Model model) {
        return "matchScreen";
    }
    @GetMapping("/stadistics")
    public String showStadistics(Model model) {
        model.addAttribute("pageTitle", "Players");
        return "stadistics";
    }
}
