package es.codeurjc.backend.RESTController;

import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import es.codeurjc.backend.DTOs.ReportDTO;
import es.codeurjc.backend.model.Matches;
import es.codeurjc.backend.model.Report;
import es.codeurjc.backend.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Get all Reports")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found reports", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Report.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
    })
    public ResponseEntity<List<ReportDTO>>getAllReports(){
        List<ReportDTO> reportDTOS = reportService.findAllReports().stream()
                .map(reportService::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(reportDTOS);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a report by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the report", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Report.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Report not found", content = @Content)
    })
    public ResponseEntity<ReportDTO>getReportId(@PathVariable Long id){
        Report report = reportService.findReportById(id);
        if (report == null){
            return ResponseEntity.notFound().build();
        }
        ReportDTO reportDTO = reportService.convertToDTO(report);
        return ResponseEntity.ok(reportDTO);

    }

    @PostMapping
    @Operation(summary = "Create a Report")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created report", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Report.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
    })
    public ResponseEntity<ReportDTO>newReportId(@RequestBody ReportDTO reportDTO){
        Report report = reportService.convertToEntity(reportDTO);
        URI location = URI.create("/api/reports"+report.getId());
        reportService.saveReport(report);
        return ResponseEntity.created(location).body(reportDTO);
    }

    @PutMapping("/{idReport}")
    @Operation(summary = "Update a report by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Updated the report", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Report.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Report not found", content = @Content)
    })
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
    @Operation(summary = "Delete a report by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Deleted the report", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Report.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "403", description = "Operation not permitted", content = @Content),
            @ApiResponse(responseCode = "404", description = "Report not found", content = @Content)
    })
    public ResponseEntity<?> deleteReport(@PathVariable long idReport){
        reportService.deleteReportById(idReport);
/*        URI location = URI.create("/api/reports");
        return ResponseEntity.ok().location(location).body(this.getAllReports().getBody());*/

        String msg = "Report with id " + idReport + " deleted .";

        return ResponseEntity.status(HttpStatus.OK).body(msg);
    }
}
