package es.codeurjc.backend.webController;

import es.codeurjc.backend.model.Player;
import es.codeurjc.backend.model.Team;
import es.codeurjc.backend.model.Tournament;
import es.codeurjc.backend.model.User;
import es.codeurjc.backend.repository.UserRepository;
import es.codeurjc.backend.service.PlayerService;
import es.codeurjc.backend.service.TeamService;
import es.codeurjc.backend.service.TournamentService;
import es.codeurjc.backend.utils.BlobConverter;
import es.codeurjc.backend.utils.UserInfoUtil;
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
public class SearchWebController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private PlayerService playerService;
    @Autowired
    private TournamentService tournamentService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserInfoUtil userInfoUtil;
    @Autowired
    private BlobConverter blobConverter;
    @GetMapping("/search")
    public String search(@RequestParam(value = "query", required = false) String query, Model model,HttpServletRequest request) throws SQLException {

        //get session id
        userInfoUtil.addUserInfoToModel(model, request);

        if (query != null && !query.isEmpty()) {
            List<Player> players = new ArrayList<>();
            query = query.toLowerCase();

            String[] parts = query.split("\\s*:\\s*", 2);

            if (parts.length == 2) {
                String field = parts[0];
                String searchTerm = parts[1];
                switch (field) {
                    case "tournament":
                        List<Tournament> tournaments = tournamentService.findTournamentByCupSearch(searchTerm);
                        for (int i=0;i<tournaments.size();i++){
                            tournaments.get(i).setTournamentImagePath(tournaments.get(i).getTournamentImageAsString());
                        }

                        model.addAttribute("tournaments", tournaments);
                        break;
                    case "team":
                        List<Team> teams = teamService.findTeamByNameSearch(searchTerm);
                        for(int i=0;i<teams.size();i++){
                            teams.get(i).setImagePath(teams.get(i).getImageAsString());
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
                    tournaments.get(i).setTournamentImagePath(tournaments.get(i).getTournamentImageAsString());
                }
                List<Team> teams = teamService.findTeamByNameSearch(query);
                for (int i=0;i<teams.size();i++){
                    teams.get(i).setImagePath(teams.get(i).getImageAsString());
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

