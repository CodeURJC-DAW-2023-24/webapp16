package es.codeurjc.backend.SampleData;


import es.codeurjc.backend.model.Player;
import es.codeurjc.backend.model.Team;
import es.codeurjc.backend.model.Tournament;
import es.codeurjc.backend.repository.TournamentRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SampleTournamentData {


    @Autowired
    private TournamentRepository tournaments;

    @PostConstruct
    public void init(){
        tournaments.save(new Tournament("Torneo1", "Futbol 7", "champions",null));
        tournaments.save(new Tournament("Torneo2", "Futbol 7", "copa del Rey",null));
        tournaments.save(new Tournament("Torneo1", "Futbol 7", "Europa League",null));
        tournaments.save(new Tournament("Torneo1", "Futbol 7", "SuperCopa",null));
    }



}