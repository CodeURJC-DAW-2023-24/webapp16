package es.codeurjc.backend.controller;

import es.codeurjc.backend.model.Player;
import es.codeurjc.backend.model.Team;
import es.codeurjc.backend.model.Tournament;
import es.codeurjc.backend.model.User;
import es.codeurjc.backend.repository.PlayerRepository;
import es.codeurjc.backend.repository.TeamRepository;
import es.codeurjc.backend.repository.UserRepository;
import es.codeurjc.backend.service.PlayerService;
import es.codeurjc.backend.service.TeamService;
import es.codeurjc.backend.service.TournamentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import java.security.Principal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private PlayerService playerService;
    @Autowired
    private TournamentService tournamentService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/search")
    public String search(@RequestParam(value = "query", required = false) String query, Model model,HttpServletRequest request) throws SQLException {

        //get session id
        Principal principal = request.getUserPrincipal();
        if(principal != null) {
            String name_session = principal.getName();
            User user = userRepository.findByName(name_session).orElseThrow();
            model.addAttribute("username", user.getName());
            if (request.isUserInRole("ADMIN")){
                model.addAttribute("admin", request.isUserInRole("ADMIN"));
                model.addAttribute("user", request.isUserInRole("USER"));
            }else
                model.addAttribute("user", request.isUserInRole("USER"));

        }






        if (query != null && !query.isEmpty()) {
            List<Player> players = new ArrayList<>();
            query = query.toLowerCase();

            String[] parts = query.split("\\s*:\\s*", 2);

            if (parts.length == 2) {
                String field = parts[0];
                String searchTerm = parts[1];
                switch (field) {
                    case "tournament":
                        List<Tournament> tournaments = tournamentService.findTournamentByCupSearch(query);
                        for(int i=0;i<tournaments.size();i++){
                            tournaments.get(i).setTournamentImagePath(tournaments.get(i).blobToString(tournaments.get(i).getTournamentImageFile(), tournaments.get(i)));
                        }

                        model.addAttribute("tournaments", tournaments);
                        break;
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
                List<Tournament> tournaments = tournamentService.findTournamentByCupSearch(query);
                for (int i=0;i<tournaments.size();i++){
                    tournaments.get(i).setTournamentImagePath(tournaments.get(i).blobToString(tournaments.get(i).getTournamentImageFile(), tournaments.get(i)));
                }
                System.out.println(tournaments);
                List<Team> teams = teamService.findTeamByNameSearch(query);
                for (int i=0;i<teams.size();i++){
                    teams.get(i).setImagePath(teams.get(i).blobToString(teams.get(i).getImageFile(), teams.get(i)));
                }
                players = playerService.findPlayerByNameSearch(query);
                players.addAll(playerService.findPlayerByLastNameSearch(query));

                model.addAttribute("tournaments", tournaments);
                model.addAttribute("teams", teams);
                model.addAttribute("players", players);
            }
            model.addAttribute("pageTitle", "Search");
        }
        return "searchResults";
    }
}

