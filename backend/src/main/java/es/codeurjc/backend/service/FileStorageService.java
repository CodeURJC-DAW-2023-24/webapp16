package es.codeurjc.backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

import org.springframework.stereotype.Service;
@Component
public class FileStorageService {

    private final Path fileStorageLocation;

    public FileStorageService(@Value("${app.uploadDir}" ) String uploadDir){
        this.fileStorageLocation = Paths.get(uploadDir);


        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new RuntimeException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public String storeFile(String base64Image, String fileName) {
        try {
            // Decode the base64 image
            String pureBase64Encoded = base64Image.substring(base64Image.indexOf(",") + 1);
            byte[] decodedBytes = Base64.getDecoder().decode(pureBase64Encoded);

            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new RuntimeException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            System.out.println(targetLocation);
            Files.write(targetLocation, decodedBytes);
            return "assets/images/" + fileName;
        } catch (IOException ex) {
            throw new RuntimeException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }
}