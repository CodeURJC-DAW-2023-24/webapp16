package es.codeurjc.backend.RESTController;

import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import es.codeurjc.backend.DTOs.ReportDTO;
import es.codeurjc.backend.model.Report;
import es.codeurjc.backend.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
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
        Report report = reportService.findReportById(id);
        if (report == null){
            return ResponseEntity.notFound().build();
        }
        ReportDTO reportDTO = reportService.convertToDTO(report);
        return ResponseEntity.ok(reportDTO);

    }

    @PostMapping
    public ResponseEntity<ReportDTO>newReportId(@RequestBody ReportDTO reportDTO){
        Report report = reportService.convertToEntity(reportDTO);
        URI location = URI.create("/api/reports"+report.getId());
        reportService.saveReport(report);
        return ResponseEntity.created(location).body(reportDTO);
    }

    @PutMapping("/{idReport}")
    public ResponseEntity<ReportDTO>updateReport(@RequestBody ReportDTO reportDTO, @PathVariable long idReport){
        Report existingReport = reportService.findReportById(idReport);
        if (existingReport == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Report report = reportService.convertToEntity(reportDTO);
        report.setId(idReport);
        Report updateReport = reportService.updateReport(report);
        ReportDTO updateReportDTO = reportService.convertToDTO(updateReport);
        return ResponseEntity.ok(updateReportDTO);
    }

    @DeleteMapping("/{idReport}")
    public ResponseEntity<?> deleteReport(@PathVariable long idReport){
        reportService.deleteReportById(idReport);
/*        URI location = URI.create("/api/reports");
        return ResponseEntity.ok().location(location).body(this.getAllReports().getBody());*/

        String msg = "Report with id " + idReport + " deleted .";

        return ResponseEntity.status(HttpStatus.OK).body(msg);
    }
}
