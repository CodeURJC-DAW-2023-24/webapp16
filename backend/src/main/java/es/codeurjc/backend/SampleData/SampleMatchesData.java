package es.codeurjc.backend.SampleData;

import java.time.LocalDate;
import es.codeurjc.backend.model.Matches;
import es.codeurjc.backend.model.Player;
import es.codeurjc.backend.model.Team;
import es.codeurjc.backend.repository.MatchRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SampleMatchesData {

    @Autowired
    private MatchRepository matches;

    @PostConstruct
    public void init(){
       matches.save(new Matches(null,null,null , 0,0, "2023-12-03", "Navalcarnero"));
    }
}
