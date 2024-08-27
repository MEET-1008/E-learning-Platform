package com.codewithmeet.elearningplatform.services;

import com.codewithmeet.elearningplatform.dtos.ResourceContantType;
import com.codewithmeet.elearningplatform.dtos.VideoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface VideoService {
    VideoDto createVideo(VideoDto videoDto);
    VideoDto updateVideo(String videoId, VideoDto videoDto);
    VideoDto getVideoById(String videoId);
    Page<VideoDto> getAllVideos(Pageable pageable);
    void deleteVideo(String videoId);
    List<VideoDto> searchVideos(String keyword);

    VideoDto addvidrofile(String videoId, MultipartFile video) throws IOException;


    ResourceContantType getVideoResource(String videoId);
}
