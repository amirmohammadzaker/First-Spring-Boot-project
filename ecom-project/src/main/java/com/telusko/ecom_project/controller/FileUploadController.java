package com.telusko.ecom_project.controller;

import com.telusko.ecom_project.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileUploadController {

    private final FileUploadService fileUploadService;

    @PostMapping(value = "/upload-temp", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadRandomImage(@RequestPart("file") MultipartFile file) throws IOException {
        String resultMessage = fileUploadService.saveFileToDisk(file);
        return new ResponseEntity<>("فایل با موفقیت ذخیره شد: " + resultMessage, HttpStatus.CREATED);
    }

 
    @PostMapping(value = "/upload-multiple-temp", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<String>> uploadMultipleImages(@RequestPart("files") List<MultipartFile> files) throws IOException {
        List<String> uploadedFiles = fileUploadService.saveMultipleFilesToDisk(files);
        return new ResponseEntity<>(uploadedFiles, HttpStatus.CREATED);
    }
}