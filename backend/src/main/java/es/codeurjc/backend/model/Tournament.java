package es.codeurjc.backend.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;
import es.codeurjc.backend.model.Tournament;
import es.codeurjc.backend.model.Team;

import javax.sql.rowset.serial.SerialBlob;


@Entity
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String category;
    private String cup;

    @Lob
    @JsonIgnore
    private Blob tournamentImageFile ;

    private String tournamentImagePath ;



    @OneToMany(mappedBy = "tournament")
    private List<Team> listTeam;

    @OneToMany(mappedBy = "tournament")
    private List<Matches> listMatches;



    public Tournament(){}
    public Tournament(String name, String category, String cup, String tournamentImagePath) {
        this.name = name;
        this.category = category;
        this.cup = cup;
        this.tournamentImagePath = tournamentImagePath;

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

    public String getCategory() {
        return this.category;
    }
    public void setCategory(String value) {
        this.category = value;
    }

    public String getCup() {
        return this.cup;
    }
    public void setCup(String value) {
        this.cup = value;
    }

    public Blob getTournamentImageFile() {
        return tournamentImageFile;
    }

    public void setTournamentImageFile(Blob tournamentImageFile) {
        this.tournamentImageFile = tournamentImageFile;
    }

    public String getTournamentImagePath() {
        return tournamentImagePath;
    }

    public void setTournamentImagePath(String tournamentImagePath) {
        this.tournamentImagePath = tournamentImagePath;
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

    public String blobToString(Blob imageFile, Tournament tournamentE) throws SQLException {
        //Convert image en base64
        //System.out.println("Este es el equipo, " + teamE.getName()+ " este es el file:   "+ teamE.getImageFile());

        Blob blob = tournamentE.getTournamentImageFile();
        byte[] bytes = blob.getBytes(1,(int) blob.length());
        String tournamentImage = Base64.getEncoder().encodeToString(bytes);
        return tournamentImage;
    }

}