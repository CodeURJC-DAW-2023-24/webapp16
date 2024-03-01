package es.codeurjc.backend.controller;

import es.codeurjc.backend.model.Player;
import es.codeurjc.backend.model.Team;
import es.codeurjc.backend.repository.PlayerRepository;
import es.codeurjc.backend.repository.TeamRepository;
import es.codeurjc.backend.service.PlayerService;
import es.codeurjc.backend.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private PlayerService playerService;

    @GetMapping("/search")
    public String search(@RequestParam(value = "query", required = false) String query, Model model) throws SQLException {
        if (query != null && !query.isEmpty()) {
            List<Player> players = new ArrayList<>();
            query = query.toLowerCase();
          //  String[] parts = query.split(":", 2);
            String[] parts = query.split("\\s*:\\s*", 2);

            if (parts.length == 2) {
                String field = parts[0];
                String searchTerm = parts[1];
                switch (field) {
                    case "team":
                        List<Team> teams = teamService.findTeamByNameSearch(searchTerm);
                        for(int i=0;i<teams.size();i++){
                            teams.get(i).setImagePath(teams.get(i).blobToString(teams.get(i).getImageFile(), teams.get(i)));
                        }

                        model.addAttribute("teams", teams);
                        break;
                    case "player":
                        players = playerService.findPlayerByNameSearch(searchTerm);
                        players.addAll(playerService.findPlayerByLastNameSearch(searchTerm));
                        model.addAttribute("players", players);
                        break;
                    case "position":
                        players = playerService.findPlayerByPositionSearch(searchTerm);
                        model.addAttribute("players", players);
                        break;
                    case "nationality":
                        players = playerService.findPlayerByNationalitySearch(searchTerm);
                        model.addAttribute("players", players);
                        break;
                    default:
                        model.addAttribute("error", "Invalid syntax. Please try again.");
                }
            } else {

                List<Team> teams = teamService.findTeamByNameSearch(query);
                for(int i=0;i<teams.size();i++){
                    teams.get(i).setImagePath(teams.get(i).blobToString(teams.get(i).getImageFile(), teams.get(i)));
                }
                players = playerService.findPlayerByNameSearch(query);
                players.addAll(playerService.findPlayerByLastNameSearch(query));
                model.addAttribute("teams", teams);
                model.addAttribute("players", players);
            }
            model.addAttribute("pageTitle", "Search");
        }
        return "searchResults";
    }
}

