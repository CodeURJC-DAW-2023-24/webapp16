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

    private String imagePath ;
    @ManyToOne
    private Tournament tournament;
    //, cascade = CascadeType.ALL, orphanRemoval = true
    @OneToMany(mappedBy = "team")
    private List<Player> listPlayer;

    protected Team(){

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



    public Blob URLtoBlob(String webURL){
        try {
            URL url = new URL(webURL);
            InputStream in = url.openStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            // Read the image data into a byte array
            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) != -1) {
                baos.write(buffer, 0, length);
            }
            in.close();
            // Convert the ByteArrayOutputStream to a byte array
            byte[] imageBytes = baos.toByteArray();
            Blob imageBlob = new SerialBlob(imageBytes);
            return imageBlob;
        } catch (IOException | SQLException e) {
            System.out.println("Error");
            return null;
        }
    }

    public String blobToString(Blob imageFile, Team teamE) throws SQLException {
        //Convert image en base64
        System.out.println("Este es el equipo, " + teamE.getName()+ " este es el file:   "+ teamE.getImageFile());

        Blob blob = teamE.getImageFile();
        byte[] bytes = blob.getBytes(1,(int) blob.length());
        String teamImage = Base64.getEncoder().encodeToString(bytes);
        return teamImage;
    }
}