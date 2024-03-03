package es.codeurjc.backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.codeurjc.backend.model.Player;
import es.codeurjc.backend.model.Team;
import es.codeurjc.backend.service.PlayerService;
import es.codeurjc.backend.service.TeamService;
import org.apache.tomcat.util.modeler.BaseAttributeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.sql.Blob;
import java.sql.SQLException;
import java.util.Comparator;
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

        //poner en el html "src= "data:image/png, base64; {{teamImage}}"
        for(int i=0;i<4;i++){
           // System.out.println("Este es el equipo, " + teams.get(i).getName()+ "   "+ teams.get(i).getImagePath());
            teams.get(i).setImagePath(teams.get(i).blobToString(teams.get(i).getImageFile(), teams.get(i)));
          //  System.out.println("Este es el equipo, " + teams.get(i).getName()+ "  este es el path modificado  "+ teams.get(i).getImagePath());

        }

        model.addAttribute("teams", teams.subList(0, Math.min(4, teams.size())));
        model.addAttribute("pageTitle", "Teams");
        return "teams";
    }


    @GetMapping("/api/teams")
    @ResponseBody
    public List<Team> getTeams(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "4") int pageSize) throws SQLException {
        Page<Team> teamsPage = teamService.findAllTeams(PageRequest.of(page, pageSize));

        for (Team currentTeam : teamsPage.getContent()) {
            currentTeam.setImagePath(currentTeam.blobToString(currentTeam.getImageFile(), currentTeam));
        }

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
    @GetMapping("/teams/{name}/{playerName}/{lastName}")
    public String showPlayerInfo(@PathVariable String name, @PathVariable String playerName,@PathVariable String lastName, Model model) {

        Team team = teamService.findTeamByName(name);
        Player player = playerService.findPlayerByNameAndLastName(playerName,lastName);


        model.addAttribute("team", team);
        model.addAttribute("player", player);

        String pagePath = team.getName() + " / " + player.getName() + " " + player.getLastName();
        model.addAttribute("pageTitle", pagePath);

        return "playerInfo";

    }
    @GetMapping("/{cup}/teamCreation/")
    public String createTournamentTeam(@PathVariable String cup, Model model){
        System.out.println("cup: "+cup);
        model.addAttribute("cup",cup);
        return "teamCreate";
    }
    @PostMapping("/addTeamToTournament/{cup}")
    public String addTeamToTournament(@PathVariable String cup, Model model){
        return ("redirect:/"+cup+"/teamCreation/");
    }
    @GetMapping("/teams/stadistics")
    public String getTeamsStadistics(Model model){


        List<Team> teams = teamService.findAll();

        teams.sort(Comparator.comparingInt(Team::getWins).reversed());

        if (teams.size() > 10) {
            teams = teams.subList(0, 10);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        String teamsJson = "";
        try {
            teamsJson = objectMapper.writeValueAsString(teams);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        model.addAttribute("teamsJson", teamsJson);
        model.addAttribute("pageTitle", "Teams Stadistics");
        return "teamsStadistics";
    }
}