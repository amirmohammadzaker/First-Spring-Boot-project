package com.telusko.ecom_project.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileUploadService {

    private static final String UPLOAD_DIR = "uploads";

    public String saveFileToDisk(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("فایل ارسالی نمی‌تواند خالی باشد");
        }

        // ۱. اطمینان از وجود پوشه /tmp/uploads
        File uploadFolder = new File(UPLOAD_DIR);
        if (!uploadFolder.exists()) {
            uploadFolder.mkdirs();
        }

        // ۲. ساخت نام منحصر به فرد برای جلوگیری از Overwrite
        String originalFilename = file.getOriginalFilename();
        String uniqueFilename = UUID.randomUUID() + "_" + (originalFilename != null ? originalFilename : "image.jpg");

        // ۳. ذخیره مستقیم فایل در دیسک
        Path destinationPath = Paths.get(UPLOAD_DIR, uniqueFilename);
        Files.copy(file.getInputStream(), destinationPath, StandardCopyOption.REPLACE_EXISTING);

        return "فایل با موفقیت ذخیره شد: " + uniqueFilename;
    }
}