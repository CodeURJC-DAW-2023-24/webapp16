package es.codeurjc.backend.Controller;

import es.codeurjc.backend.model.Team;
import es.codeurjc.backend.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class TeamControler {

    @Autowired
    private TeamService teamService;
    @GetMapping("/teams")
    public String showTeams(Model model) {

        List<Team> teams = teamService.findAll();
        model.addAttribute("teams", teams);
        return "team";
    }
}
