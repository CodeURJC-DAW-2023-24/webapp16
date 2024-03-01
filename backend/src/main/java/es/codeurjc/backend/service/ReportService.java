package es.codeurjc.backend.service;

import es.codeurjc.backend.model.Report;
import es.codeurjc.backend.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    public List<Report> findAllReports(){
        return reportRepository.findAll();
    }
    public Optional<Report> findReportById(Long id) {
        return reportRepository.findById(id);
    }
    public Report saveReport(Report report){
        return reportRepository.save(report);
    }
    public void deleteReport(Long id) {
        reportRepository.deleteById(id);
    }
    public Report findReportByMatchId(Long id){
        return reportRepository.findReportByMatch_Id(id);
    }
}
