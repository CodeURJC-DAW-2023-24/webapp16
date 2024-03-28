package es.codeurjc.backend.service;

import es.codeurjc.backend.DTOs.ReportDTO;
import es.codeurjc.backend.DTOs.UserDTO;
import es.codeurjc.backend.model.Matches;
import es.codeurjc.backend.model.Report;
import es.codeurjc.backend.model.User;
import es.codeurjc.backend.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportService {
    @Autowired
    private ConversionService conversionService;

    @Autowired
    private ReportRepository reportRepository;

    public List<Report> findAllReports(){return reportRepository.findAll();}
    public Optional<Report> findReportById(Long id) {
        return reportRepository.findById(id);
    }
    public Report saveReport(Report report){
        return reportRepository.save(report);
    }
    public void deleteReport(Report report) {
        reportRepository.delete(report);
    }
    public Report findReportByMatchId(Long id){
        return reportRepository.findReportByMatch_Id(id);
    }
    public void newReport(String date, String time, String matchOfficials, int localTeamGoals, int visitingTeamGoals, String observations, Matches match){
        Report reportNew = new Report(date, time, matchOfficials, localTeamGoals, visitingTeamGoals, observations, match);
        reportNew.setDate(date);
        reportNew.setTime(time);
        reportNew.setMatchOfficials(matchOfficials);
        reportNew.setLocalTeamGoals(localTeamGoals);
        reportNew.setVisitingTeamGoals(visitingTeamGoals);
        reportNew.setObservations(observations);
        reportNew.setMatch(match);
    }
    public void updateReport(Report report){
        Report DBreport = reportRepository.getReferenceById(report.getId());
        if (report.getDate() != null){
            DBreport.setDate(report.getDate());
        }if (report.getTime() != null){
            DBreport.setTime(report.getTime());
        }if (report.getMatchOfficials() != null){
            DBreport.setMatchOfficials(report.getMatchOfficials());
        }if (report.getLocalTeamGoals() != 0){
            DBreport.setLocalTeamGoals(report.getLocalTeamGoals());
        }if (report.getVisitingTeamGoals() != 0){
            DBreport.setVisitingTeamGoals(report.getVisitingTeamGoals());
        }if (report.getObservations() != null){
            DBreport.setObservations(report.getObservations());
        }if (report.getMatch() != null){
            DBreport.setMatch(report.getMatch());
        }
        reportRepository.save(DBreport);
    }

    public ReportDTO convertToDTO(Report report) {
        return conversionService.convertToDTO(report, ReportDTO.class);
    }
    public void deleteReportById(Long id){reportRepository.deleteById(id);}

    public Report convertToEntity(ReportDTO reportDTO) {
        return conversionService.convertToEntity(reportDTO, Report.class);
    }
}
