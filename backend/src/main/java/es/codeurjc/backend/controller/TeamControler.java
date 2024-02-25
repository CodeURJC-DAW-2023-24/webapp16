package es.codeurjc.backend.controller;

import es.codeurjc.backend.model.Player;
import es.codeurjc.backend.model.Team;
import es.codeurjc.backend.service.PlayerService;
import es.codeurjc.backend.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class TeamControler {

    @Autowired
    private TeamService teamService;
    @Autowired
    private PlayerService playerService;
    @GetMapping("/teams")
    public String showTeams(Model model) {

        List<Team> teams = teamService.findAll();
        model.addAttribute("teams", teams);
        return "team";
    }
    @GetMapping("/teams/{name}")
    public String showTeamInfo(@PathVariable String name, Model model) {
        Team team = teamService.findTeamByName(name);
        List<Player> players = playerService.findPlayerTeamById(team.getId());

        model.addAttribute("team", team);
        model.addAttribute("players", players);
        return "teamInfo";

    }
}
