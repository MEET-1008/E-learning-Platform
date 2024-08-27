package com.codewithmeet.elearningplatform.services.impl;

import com.codewithmeet.elearningplatform.services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


@Service
public class FileServiceImpl implements FileService {
    @Override
    public String savefile(MultipartFile file, String outputPath, String filename) throws IOException {

        Path path = Paths.get(outputPath);

//         create a output folder if not exist
        Files.createDirectories(path);

        Path filepath = Paths.get(path.toString(), file.getOriginalFilename());
        System.out.println(filepath);

//         copy file
        Files.copy(file.getInputStream(), filepath, StandardCopyOption.REPLACE_EXISTING);

        return filepath.toString();
    }
}
