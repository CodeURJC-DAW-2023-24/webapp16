package es.codeurjc.webapp16;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MustacheController {
	
	@GetMapping("/tournament_bracket")
	public String tour(Model model) {
		return "tournament_bracket";
	}
    @GetMapping("/header")
	public String haed(Model model) {
		return "header";
	}
    @GetMapping("/footer")
	public String foot(Model model) {
		return "footer";
	}
	@GetMapping("/prueba")
	public String pru(Model model) {
		return "prueba";
	}
	@GetMapping("/page_banner")
    public String banner(Model model) {
        return "page_banner";
    }
	@GetMapping("/login")
    public String login(Model model) {
        return "regLog";
    }
	@GetMapping("/createTournament")
    public String newTour(Model model) {
        return "tournament-create";
    }
	@GetMapping("/index")
    public String index(Model model) {
        return "index";
    }

	@GetMapping("/teamInfo")
    public String teaminfo(Model model) {
        return "teamInfo";
	}
	@GetMapping("/about")
    public String about(Model model) {
        return "about";
		}
	@GetMapping("/fillMatchReport")
    public String report(Model model) {
        return "FillMatchReport";
	}
	@GetMapping("/profile")
    public String profile(Model model) {
        return "profile";
    }
}
