package es.codeurjc.backend.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import es.codeurjc.backend.utils.BlobConverter;
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
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"name"})})
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private String category;
    private String cup;
    @Lob
    @JsonIgnore
    private Blob tournamentImageFile ;
    @Column(length = 2048)
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

    public String getTournamentImageAsString() throws SQLException {
        BlobConverter converter = new BlobConverter();
        return converter.blobToString(this.getTournamentImageFile());
    }
}