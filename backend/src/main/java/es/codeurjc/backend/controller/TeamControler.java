package es.codeurjc.backend.controller;

import es.codeurjc.backend.model.Player;
import es.codeurjc.backend.model.Team;
import es.codeurjc.backend.service.PlayerService;
import es.codeurjc.backend.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;


import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Base64;

@Controller
public class TeamControler {

    @Autowired
    private TeamService teamService;
    @Autowired
    private PlayerService playerService;
    @GetMapping("/teams")
    public String showTeams(Model model) throws SQLException {

        List<Team> teams = teamService.findAll();

        for(int i=0;i<teams.size();i++){
            teams.get(i).setImagePath(teams.get(i).blobToString(teams.get(i).getImageFile(), teams.get(i)));
          //  System.out.println("Este es el equipo, " + teams.get(i).getName()+ "  este es el path modificado  "+ teams.get(i).getImagePath());

        }

        model.addAttribute("teams", teams);
        model.addAttribute("pageTitle", "Teams");
        return "teams";
    }

    @GetMapping("/api/teams")
    @ResponseBody
    public List<Team> getTeams(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "4") int pageSize) {
        Page<Team> teamsPage = teamService.findAllTeams(PageRequest.of(page, pageSize));
        return teamsPage.getContent();
    }


    @GetMapping("/teams/{name}")
    public String showTeamInfo(@PathVariable String name, Model model) {
        Team team = teamService.findTeamByName(name);

        List<Player> players = playerService.findPlayerTeamById(team.getId());

        model.addAttribute("team", team);
        model.addAttribute("players", players);

        model.addAttribute("pageTitle", team.getName());

        return "teamInfo";

    }
    @GetMapping("/teams/{name}/{playerName}")
    public String showPlayerInfo(@PathVariable String name, @PathVariable String playerName, Model model) {

        Team team = teamService.findTeamByName(name);
        Player player = playerService.findPlayerByName(playerName);


        model.addAttribute("team", team);
        model.addAttribute("player", player);

        String pagePath = team.getName() + " / " + player.getName() + " " + player.getLastName();
        model.addAttribute("pageTitle", pagePath);

        return "playerInfo";

    }

}