package com.codewithmeet.elearningplatform.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {

    String savefile (MultipartFile file , String path , String filename) throws IOException;


}
