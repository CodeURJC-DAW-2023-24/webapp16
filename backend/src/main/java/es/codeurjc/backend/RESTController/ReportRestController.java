package es.codeurjc.backend.RESTController;

import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import es.codeurjc.backend.DTOs.ReportDTO;
import es.codeurjc.backend.DTOs.TeamDTO;
import es.codeurjc.backend.model.Matches;
import es.codeurjc.backend.model.Player;
import es.codeurjc.backend.model.Report;
import es.codeurjc.backend.model.Team;
import es.codeurjc.backend.service.MatchService;
import es.codeurjc.backend.service.PlayerService;
import es.codeurjc.backend.service.ReportService;
import es.codeurjc.backend.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reports")
public class ReportRestController {
    @Autowired
    private ReportService reportService;
    @GetMapping
    public ResponseEntity<List<ReportDTO>>getAllReports(){
        List<ReportDTO> reportDTOS = reportService.findAllReports().stream()
                .map(reportService::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(reportDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReportDTO>getReportId(@PathVariable Long id){
        Optional<Report> report = reportService.findReportById(id);
        if (report.isPresent()){
            ReportDTO reportDTO = reportService.convertToDTO(report.get());
            return ResponseEntity.ok(reportDTO);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ReportDTO>newReportId(@RequestBody ReportDTO reportDTO){
        Report report = reportService.convertToEntity(reportDTO);
        URI location = URI.create("/api/reports"+report.getId());
        reportService.saveReport(report);
        return ResponseEntity.created(location).body(reportDTO);
    }

    @PutMapping
    public ResponseEntity<ReportDTO>updateReport(@RequestBody ReportDTO reportDTO){
        Report report = reportService.convertToEntity(reportDTO);
        URI location = URI.create("/api/reports"+report.getId());
        reportService.updateReport(report);
        return ResponseEntity.created(location).body(reportDTO);
    }

    @DeleteMapping("/{idReport}")
    public ResponseEntity<List<ReportDTO>> deleteReport(@PathVariable long idReport){
        reportService.deleteReportById(idReport);
        URI location = URI.create("/api/reports");
        return ResponseEntity.ok().location(location).body(this.getAllReports().getBody());
    }
}
