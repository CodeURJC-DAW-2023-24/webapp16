package es.codeurjc.backend.DTOs;

public class TournamentDTO {

    private Long id;
    private String name;
    private String category;
    private String cup;
    private String tournamentImagePath;

    // Constructor vacío
    public TournamentDTO() {
    }

    // Constructor con parámetros
    public TournamentDTO(Long id, String name, String category, String cup, String tournamentImagePath) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.cup = cup;
        this.tournamentImagePath = tournamentImagePath;
    }

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCup() {
        return cup;
    }

    public void setCup(String cup) {
        this.cup = cup;
    }

    public String getTournamentImagePath() {
        return tournamentImagePath;
    }

    public void setTournamentImagePath(String tournamentImagePath) {
        this.tournamentImagePath = tournamentImagePath;
    }
}
