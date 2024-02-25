package es.codeurjc.backend.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MustacheController {
	
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
	@GetMapping("/tournamentCreation")
    public String newTour(Model model) {
        return "tournamentCreate";
    }
	@GetMapping("/index")
    public String index(Model model) {
        return "index";
    }

	@GetMapping("/about")
    public String about(Model model) {
        return "about";
		}
	@GetMapping("/fillMatchReport")
    public String report(Model model) {
        return "fillMatchReport";
	}
	@GetMapping("/profile")
    public String profile(Model model) {
        return "profile";
    }
	//@GetMapping("/team")
    //public String team(Model model) {
        //return "team";
    //}
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
        return "teamCreate";
    }
	@GetMapping("/matchScreen")
    public String showMatchScreen(Model model) {
        return "matchScreen";
    }
    @GetMapping("/stadistics")
    public String showStadistics(Model model) {
        return "stadistics";
    }
}
