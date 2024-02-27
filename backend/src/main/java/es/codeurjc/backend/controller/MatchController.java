package es.codeurjc.backend.controller;

import es.codeurjc.backend.model.Player;
import es.codeurjc.backend.model.Team;
import es.codeurjc.backend.service.PlayerService;
import org.springframework.ui.Model;
import es.codeurjc.backend.model.Matches;
import es.codeurjc.backend.model.Tournament;
import es.codeurjc.backend.service.MatchService;
import es.codeurjc.backend.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.SQLException;
import java.util.List;

@Controller
public class MatchController {
    @Autowired
    private MatchService matchService;

    @Autowired
    private TournamentService tournamentService;


    @Autowired
    private PlayerService playerService;


    @GetMapping("/tournament/{cup}/{id}") //si quitas el index pierde los css
    public String showMatch(Model model,@PathVariable String cup, @PathVariable  Long id) throws SQLException {
        Tournament tournament =  tournamentService.findTournamentByCup(cup);
        Matches match = matchService.findMatchById(id);

        Team localTeam = match.getLocalTeam();
        Team visitingTeam = match.getVisitingTeam();

        List <Player> playerListLocal = playerService.findPlayerTeamById(localTeam.getId());
        List <Player> playerListVisiting = playerService.findPlayerTeamById(visitingTeam.getId());

        localTeam.setImagePath(localTeam.blobToString(localTeam.getImageFile(), localTeam));
        visitingTeam.setImagePath(visitingTeam.blobToString(visitingTeam.getImageFile(), visitingTeam));



        model.addAttribute("localTeam",localTeam);
        model.addAttribute("visitingTeam", visitingTeam);

        model.addAttribute("playerListLocal", playerListLocal);
        model.addAttribute("playerListVisiting", playerListVisiting);

        String pagePath = localTeam.getName() + " vs " + visitingTeam.getName();
        model.addAttribute("pageTitle", pagePath);

        return "matchScreen";
    }





}
