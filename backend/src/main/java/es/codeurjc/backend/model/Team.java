package es.codeurjc.backend.model;




import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import es.codeurjc.backend.utils.BlobConverter;
import jakarta.persistence.*;

import javax.sql.rowset.serial.SerialBlob;

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

    @Lob
    @JsonIgnore
    private Blob imageFile ;
    @Column(length = 1024)
    private String imagePath ;
    @ManyToOne
    private Tournament tournament;
    @OneToMany(mappedBy = "team")
    private List<Player> listPlayer;

    protected Team(){
    }
    public List<Player> getListPlayer() {
        return listPlayer;
    }

    public void setListPlayer(List<Player> listPlayer) {
        this.listPlayer = listPlayer;
    }

    public Team(String name, String coach, String stadium, Tournament tournament, int gamesPlayed, int wins, int loses, String imagePath) {
        this.name = name;
        this.coach = coach;
        this.stadium = stadium;
        this.tournament = tournament;
        this.gamesPlayed = gamesPlayed;
        this.wins = wins;
        this.loses = loses;
        this.imagePath = imagePath;

    }

    public Blob getImageFile() {
        return imageFile;
    }

    public void setImageFile(Blob imageFile) {
        this.imageFile = imageFile;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

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

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public String getImageAsString() throws SQLException {
        BlobConverter converter = new BlobConverter();
        return converter.blobToString(this.getImageFile());
    }
}