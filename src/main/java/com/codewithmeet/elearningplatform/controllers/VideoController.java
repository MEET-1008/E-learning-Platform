package com.codewithmeet.elearningplatform.controllers;

import com.codewithmeet.elearningplatform.dtos.VideoDto;
import com.codewithmeet.elearningplatform.services.VideoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/videos")
public class VideoController {


    private final VideoService videoService;

    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @PostMapping
    public ResponseEntity<VideoDto> createVideo(@RequestBody VideoDto videoDto) {
        return ResponseEntity.ok(videoService.createVideo(videoDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VideoDto> updateVideo(@PathVariable String id, @RequestBody VideoDto videoDto) {
        return ResponseEntity.ok(videoService.updateVideo(id, videoDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VideoDto> getVideoById(@PathVariable String id) {
        return ResponseEntity.ok(videoService.getVideoById(id));
    }

    @GetMapping
    public ResponseEntity<Page<VideoDto>> getAllVideos(Pageable pageable) {
        return ResponseEntity.ok(videoService.getAllVideos(pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVideo(@PathVariable String id) {
        videoService.deleteVideo(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<VideoDto>> searchVideos(@RequestParam String keyword) {
        return ResponseEntity.ok(videoService.searchVideos(keyword));
    }

    @PostMapping("/{videoId}/video")
    public ResponseEntity<VideoDto> addVideo(
            @PathVariable String videoId,
            @RequestParam MultipartFile file) throws IOException {

        VideoDto addvidrofile = videoService.addvidrofile(videoId, file);

        return ResponseEntity.ok(addvidrofile);

    }





}
