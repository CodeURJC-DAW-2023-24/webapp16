package es.codeurjc.backend.repository;

import es.codeurjc.backend.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {

    Report findReportByMatch_Id(Long id);
}
