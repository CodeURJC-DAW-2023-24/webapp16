package es.codeurjc.backend.repository;

import es.codeurjc.backend.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {

    @Query("SELECT r FROM Report r")
    List<Report> findAllReports();

    @Query("SELECT r FROM Report r WHERE r.id = :id")
    Report findReportById(@Param("id") Long id);

    Report findReportByMatch_Id(Long id);
}
