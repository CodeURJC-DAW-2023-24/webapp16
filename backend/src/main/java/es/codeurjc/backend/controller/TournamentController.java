package es.codeurjc.backend.controller;


import es.codeurjc.backend.model.Matches;
import es.codeurjc.backend.service.MatchService;
import org.springframework.ui.Model;
import es.codeurjc.backend.model.Tournament;
import es.codeurjc.backend.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;

@Controller
public class TournamentController {

    @Autowired
    private TournamentService tournamentService;

    @Autowired MatchService matchService;


    @GetMapping("/index")
    public String showTournaments(Model model){

        List<Tournament> tournaments = tournamentService.findAllTournaments();
        model.addAttribute("tournaments", tournaments);

        model.addAttribute("pageTitle", "Tournaments");
        return "index";


    }
    @GetMapping("/{cup}")
    public String showBracket(Model model, @PathVariable String cup){
        Tournament tournament =  tournamentService.findTournamentByCup(cup);
        //  List<Matches> tournamentBracket = tournamentService.findBracketById(tournament.getId()); // partidos con el id de un tournament
        List<Matches> roundOne = tournamentService.findRound(1, tournament.getId());
        List<Matches> roundTwo = tournamentService.findRound(2, tournament.getId());
        List<Matches> roundThree = tournamentService.findRound(3, tournament.getId());
        List<Matches> roundFour = tournamentService.findRound(4, tournament.getId());


        model.addAttribute("cup",cup);
        model.addAttribute("roundOne", roundOne);
        model.addAttribute("roundTwo", roundTwo);
        model.addAttribute("roundThree", roundThree);
        model.addAttribute("roundFour", roundFour);
        model.addAttribute("pageTitle", cup);

        return "tournamentBracket";
    }









}
