package es.codeurjc.backend.utils;

import org.springframework.stereotype.Component;

import java.sql.Blob;
import java.sql.SQLException;

@Component
public class BlobConverter {

    public Blob fileToBlob(byte[] fileBytes) throws SQLException {
        try {
            return new javax.sql.rowset.serial.SerialBlob(fileBytes);
        } catch (SQLException e) {
            throw new SQLException("Error al convertir bytes a Blob", e);
        }
    }
}
