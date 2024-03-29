package es.codeurjc.backend.utils;

import org.springframework.stereotype.Component;

import javax.sql.rowset.serial.SerialBlob;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;

@Component
public class BlobConverter {

    public Blob fileToBlob(byte[] fileBytes) throws SQLException {
        try {
            return new javax.sql.rowset.serial.SerialBlob(fileBytes);
        } catch (SQLException e) {
            throw new SQLException("Error al convertir bytes a Blob", e);
        }
    }
    public Blob URLtoBlob(String webURL) {
        try {
            URL url = new URL(webURL);
            InputStream in = url.openStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) != -1) {
                baos.write(buffer, 0, length);
            }
            in.close();
            byte[] imageBytes = baos.toByteArray();
            Blob imageBlob = new SerialBlob(imageBytes);
            return imageBlob;
        } catch (IOException | SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
    public String blobToString(Blob imageFile) throws SQLException {
        if (imageFile!=null) {
            byte[] bytes = imageFile.getBytes(1, (int) imageFile.length());
            String imageString = Base64.getEncoder().encodeToString(bytes);
            return imageString;
        }else return "";
    }
}
