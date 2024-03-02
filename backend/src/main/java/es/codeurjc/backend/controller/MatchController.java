package es.codeurjc.backend.controller;

import es.codeurjc.backend.model.*;
import es.codeurjc.backend.repository.ReportRepository;
import es.codeurjc.backend.service.PlayerService;
import es.codeurjc.backend.service.ReportService;
import org.springframework.ui.Model;
import es.codeurjc.backend.service.MatchService;
import es.codeurjc.backend.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@Controller
public class MatchController {
    @Autowired
    private MatchService matchService;

    @Autowired
    private TournamentService tournamentService;


    @Autowired
    private PlayerService playerService;

    @Autowired
    private ReportService reportService;


    @GetMapping("/tournament/{cup}/{id}")
    public String showMatch(Model model,@PathVariable String cup, @PathVariable  Long id) throws SQLException {
        Tournament tournament =  tournamentService.findTournamentByCup(cup);
        Matches match = matchService.findMatchById(id);

        Team localTeam = match.getLocalTeam();
        Team visitingTeam = match.getVisitingTeam();

        List <Player> playerListLocal = playerService.findPlayerTeamById(localTeam.getId());
        List <Player> playerListVisiting = playerService.findPlayerTeamById(visitingTeam.getId());

        localTeam.setImagePath(localTeam.blobToString(localTeam.getImageFile(), localTeam));
        visitingTeam.setImagePath(visitingTeam.blobToString(visitingTeam.getImageFile(), visitingTeam));



        model.addAttribute("localTeam",localTeam);
        model.addAttribute("visitingTeam", visitingTeam);

        model.addAttribute("playerListLocal", playerListLocal);
        model.addAttribute("playerListVisiting", playerListVisiting);

        String pagePath = localTeam.getName() + " vs " + visitingTeam.getName();
        model.addAttribute("pageTitle", pagePath);

        return "matchScreen";
    }

    @GetMapping("/tournament/{cup}/{id}/fillMatchReport")
    public String getFillMatchReportForm(@PathVariable Long id, @PathVariable String cup,  Model model) {
        Tournament tournament =  tournamentService.findTournamentByCup(cup);
        Matches match = matchService.findMatchById(id);

        if (match != null && match.getReport() != null) {
            return "/error"; //Incluir algo como ya hay un reporte para ese partido
        } else {
            model.addAttribute("match", match);
            model.addAttribute("report", new Report());

        }

        return "fillMatchReport";
    }

    @PostMapping("/tournament/{cup}/{id}/fillMatchReport/saved")
    public String fillMatchReport(Model model, @PathVariable Long id, @PathVariable String cup,
                                  @RequestParam("dateOfBirth") String dateOfBirth, @RequestParam("matchTime") String matchTime,
                                  @RequestParam("team1Goals") int team1Goals, @RequestParam("team2Goals") int team2Goals,
                                  @RequestParam("matchOfficials") String matchOfficials, @RequestParam("matchSummary") String matchSummary) {
        Tournament tournament =  tournamentService.findTournamentByCup(cup);
        Matches match = matchService.findMatchById(id);
        model.addAttribute("pageTitle", "Report");

        if (match != null) {

            if (team1Goals < 0 || team2Goals < 0) {

                model.addAttribute("error", "Goals cannot be negative.");
                return "/error";
            }
            if (team1Goals == team2Goals) {

                model.addAttribute("error", "It is not possible to save a match with a tie result.");
                return "/error";
            }

            Report report = new Report();
            report.setDate(dateOfBirth);
            report.setTime(matchTime);
            report.setMatchOfficials(matchOfficials);
            report.setLocalTeamGoals(team1Goals);
            report.setVisitingTeamGoals(team2Goals);
            report.setObservations(matchSummary);
            report.setMatch(match);

            match.setLocalGoals(team1Goals);
            match.setVisitingGoals(team2Goals);


            Team winner;
            Team loser;
            if (team1Goals > team2Goals){

                winner = match.getLocalTeam();
                winner.setGamesPlayed(winner.getGamesPlayed() + 1);
                winner.setWins(winner.getWins() + 1);

                loser = match.getVisitingTeam();
                loser.setGamesPlayed(loser.getGamesPlayed() + 1);
                loser.setLoses(loser.getLoses() + 1);
            } else {
                winner = match.getVisitingTeam();
                winner.setWins(winner.getWins() + 1);
                loser = match.getLocalTeam();
                loser.setLoses(loser.getLoses() + 1);
                winner = match.getVisitingTeam();}

            reportService.saveReport(report);

            if (match.getId() % 2 != 0) {
                Matches nextMatch = new Matches();
                nextMatch.setTournament(tournament);
                nextMatch.setRound(match.getRound() + 1);

                nextMatch.setLocalTeam(winner);
                nextMatch.setLocalGoals(0);

                matchService.saveMatch(nextMatch);
            } else {
                List<Matches> listNextMatches = matchService.findMatchByRound(match.getRound() + 1);

                for (Matches nextMatch : listNextMatches) {
                    if (nextMatch.getVisitingTeam() == null) {
                        nextMatch.setVisitingTeam(winner);
                        nextMatch.setVisitingGoals(0);
                        matchService.saveMatch(nextMatch);
                        break;
                    }

                }
            }

            model.addAttribute("report", report);
            model.addAttribute("match", match);
            return "showReport";
        } else {
            return "/error";
        }
    }
    @GetMapping("/tournament/{cup}/{id}/report")
    public String showMatchReport(Model model, @PathVariable Long id, @PathVariable String cup) {
        Tournament tournament =  tournamentService.findTournamentByCup(cup);
        Matches match = matchService.findMatchById(id);

        Report report = reportService.findReportByMatchId(id);

        if (report != null) {
            model.addAttribute("report", report);
            model.addAttribute("match", match);
        } else{
            model.addAttribute("error", "The report for this match is not available. Please wait for it to be filled in.");
        }

        model.addAttribute("pageTitle", "Report");
        return "showReport";
    }


}
