package es.codeurjc.backend.Controller;


import org.springframework.ui.Model;
import es.codeurjc.backend.model.Team;
import es.codeurjc.backend.model.Tournament;
import es.codeurjc.backend.repository.TournamentRepository;
import es.codeurjc.backend.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.List;

@Controller
public class TournamentController {

    @Autowired
    private TournamentService tournamentService;



    @GetMapping("/index")
    public String showTournaments(Model model){

        List<Tournament> tournaments = tournamentService.findAllTournaments();
        model.addAttribute("tournaments", tournaments);
        return "index";


    }




}
