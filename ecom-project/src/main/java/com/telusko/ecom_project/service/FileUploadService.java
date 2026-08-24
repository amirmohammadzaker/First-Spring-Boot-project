package com.telusko.ecom_project.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FileUploadService {

    private static final String UPLOAD_DIR = "uploads";

    public String saveFileToDisk(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("فایل ارسالی نمی‌تواند خالی باشد");
        }

        File uploadFolder = new File(UPLOAD_DIR);
        if (!uploadFolder.exists()) {
            uploadFolder.mkdirs();
        }

        String originalFilename = file.getOriginalFilename();
        String uniqueFilename = UUID.randomUUID() + "_" + (originalFilename != null ? originalFilename : "image.jpg");

        Path destinationPath = Paths.get(UPLOAD_DIR, uniqueFilename);
        Files.copy(file.getInputStream(), destinationPath, StandardCopyOption.REPLACE_EXISTING);

        return uniqueFilename;
    }

    public List<String> saveMultipleFilesToDisk(List<MultipartFile> files) throws IOException {
        if (files == null || files.isEmpty()) {
            throw new IllegalArgumentException("لیست فایل‌ها نمی‌تواند خالی باشد");
        }

        List<String> uploadedFileNames = new ArrayList<>();

        for (MultipartFile file : files) {
            String savedFileName = saveFileToDisk(file);
            uploadedFileNames.add(savedFileName);
        }

        return uploadedFileNames;
    }
}