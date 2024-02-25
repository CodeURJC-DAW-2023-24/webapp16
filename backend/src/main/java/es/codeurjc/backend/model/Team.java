package es.codeurjc.backend.model;




import java.util.List;

import jakarta.persistence.*;
import es.codeurjc.backend.model.Tournament;
@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String coach;
    private String stadium;
    private int gamesPlayed;
    private int wins;
    private int loses;
    @ManyToOne
    private Tournament tournament;

    @OneToMany(mappedBy = "team")
    private List<Player> listPlayer;

    protected Team(){

    }

    public Team(String name, String coach, String stadium, Tournament tournament, int gamesPlayed, int wins, int loses) {
        this.name = name;
        this.coach = coach;
        this.stadium = stadium;
        this.tournament = tournament;
        this.gamesPlayed = gamesPlayed;
        this.wins = wins;
        this.loses = loses;

    }

    // Relación de uno a muchos con la clase player
    public Long getId() {
      return this.id;
    }
    public void setId(Long value) {
      this.id = value;
    }

    public String getName() {
      return this.name;
    }
    public void setName(String value) {
      this.name = value;
    }

    public String getCoach() {
      return this.coach;
    }
    public void setCoach(String value) {
      this.coach = value;
    }

    public String getStadium() {
      return this.stadium;
    }
    public void setStadium(String value) {
      this.stadium = value;
    }
    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public int getWins() {
        return wins;
    }

    public int getLoses() {
        return loses;
    }
    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public void setLoses(int loses) {
        this.loses = loses;
    }

}