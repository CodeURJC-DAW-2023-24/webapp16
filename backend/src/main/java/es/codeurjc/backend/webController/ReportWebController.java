package es.codeurjc.backend.webController;

import es.codeurjc.backend.model.Matches;
import es.codeurjc.backend.model.Report;
import es.codeurjc.backend.model.Team;
import es.codeurjc.backend.model.Tournament;
import es.codeurjc.backend.repository.UserRepository;
import es.codeurjc.backend.service.MatchService;
import es.codeurjc.backend.service.PlayerService;
import es.codeurjc.backend.service.ReportService;
import es.codeurjc.backend.service.TournamentService;
import es.codeurjc.backend.utils.UserInfoUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ReportWebController {

    @Autowired
    private MatchService matchService;
    @Autowired
    private TournamentService tournamentService;

    @Autowired
    private ReportService reportService;
    @Autowired
    private UserInfoUtil userInfoUtil;

    @GetMapping("/tournament/{cup}/{id}/fillMatchReport")
    public String getFillMatchReportForm(@PathVariable Long id, @PathVariable String cup, Model model) {
        Tournament tournament =  tournamentService.findTournamentByCup(cup);
        Matches match = matchService.findMatchById(id);

        if (match != null && match.getReport() != null) {
            model.addAttribute("errorMessage", "Match report already filled");
            return "/errorTemplate";
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

                model.addAttribute("errorMessage", "Goals cannot be negative.");
                return "/errorTemplate";
            }
            if (team1Goals == team2Goals) {

                model.addAttribute("errorMessage", "It is not possible to save a match with a tie result.");
                return "/errorTemplate";
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

            List<Matches> nextRoundMatches = matchService.findMatchByRoundAndCup(match.getRound() + 1, tournament);
            List<Matches> actualRound = matchService.findMatchByRoundAndCup(match.getRound(), tournament);
            if (nextRoundMatches.isEmpty()) {
                for (int i = 0; i < actualRound.size(); i+=2){
                    Matches nextMatch = new Matches();
                    nextMatch.setTournament(tournament);
                    nextMatch.setRound(match.getRound() + 1);
                    System.out.println("matches saved");
                    nextRoundMatches.add(nextMatch);
                }
                nextRoundMatches.get(0).setLocalTeam(winner);
                nextRoundMatches.get(0).setLocalGoals(0);
                for (Matches nextMatch : nextRoundMatches){
                    matchService.saveMatch(nextMatch);
                }
            } else {
                List<Matches> listNextMatches = matchService.findMatchByRoundAndCup(match.getRound() + 1, tournament);

                for (Matches nextMatch : listNextMatches) {
                    if (nextMatch.getLocalTeam() == null) {
                        nextMatch.setLocalTeam(winner);
                        nextMatch.setLocalGoals(0);
                        matchService.saveMatch(nextMatch);
                        break;
                    }
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
            return "/errorTemplate";
        }
    }
    @GetMapping("/tournament/{cup}/{id}/report")
    public String showMatchReport(Model model, @PathVariable Long id, @PathVariable String cup, HttpServletRequest request) {
        //get session id
        userInfoUtil.addUserInfoToModel(model, request);

        //get tournament by cup in path
        Tournament tournament =  tournamentService.findTournamentByCup(cup);
        //get match by id in path
        Matches match = matchService.findMatchById(id);
        //get report by match_id
        Report report = reportService.findReportByMatchId(id);


        //report exists or not
        if (report != null) {
            model.addAttribute("report", report);
            model.addAttribute("match", match);
        } else {
            model.addAttribute("errorMessage", "The report for this match is not available. Please wait for it to be filled in.");
        }
        //add pageTitle to page_banner
        model.addAttribute("pageTitle", "Report");
        return "showReport";
    }
}
