package com.codewithmeet.elearningplatform.services.impl;

import com.codewithmeet.elearningplatform.config.AppConstants;
import com.codewithmeet.elearningplatform.dtos.ResourceContantType;
import com.codewithmeet.elearningplatform.dtos.VideoDto;
import com.codewithmeet.elearningplatform.entities.Video;
import com.codewithmeet.elearningplatform.repositories.UserRepo;
import com.codewithmeet.elearningplatform.repositories.VideoRepo;
import com.codewithmeet.elearningplatform.services.FileService;
import com.codewithmeet.elearningplatform.services.VideoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    private VideoRepo videoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    FileService fileService;

    @Override
    public VideoDto createVideo(VideoDto videoDto) {
        videoDto.setVideoId(UUID.randomUUID().toString());
        Video video = modelMapper.map(videoDto, Video.class);
        Video savedVideo = videoRepository.save(video);
        return modelMapper.map(savedVideo, VideoDto.class);
    }

    @Override
    public VideoDto updateVideo(String videoId, VideoDto videoDto) {
        Video video = videoRepository.findById(videoId)
                .orElseThrow(() -> new RuntimeException("Video not found"));
        modelMapper.map(videoDto, video);
        Video updatedVideo = videoRepository.save(video);
        return modelMapper.map(updatedVideo, VideoDto.class);
    }

    @Override
    public VideoDto getVideoById(String videoId) {
        Video video = videoRepository.findById(videoId)
                .orElseThrow(() -> new RuntimeException("Video not found"));
        return modelMapper.map(video, VideoDto.class);
    }

    @Override
    public Page<VideoDto> getAllVideos(Pageable pageable) {
        Page<Video> videos = videoRepository.findAll(pageable);
        List<VideoDto> dtos = videos.getContent()
                .stream()
                .map(video -> modelMapper.map(video, VideoDto.class))
                .collect(Collectors.toList());
        return new PageImpl<>(dtos, pageable, videos.getTotalElements());
    }

    @Override
    public void deleteVideo(String videoId) {
        videoRepository.deleteById(videoId);
    }

    @Override
    public List<VideoDto> searchVideos(String keyword) {
        List<Video> videos = videoRepository.findByTitleContainingIgnoreCaseOrDescContainingIgnoreCase(keyword, keyword);
        return videos.stream()
                .map(video -> modelMapper.map(video, VideoDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public VideoDto addvidrofile(String videoId, MultipartFile file) throws IOException {
        Video video = videoRepository.findById(videoId).orElseThrow(() -> new RuntimeException("Video not found"));

        String videopath = fileService.savefile(file, AppConstants.video_UPLOAD_PATH, file.getOriginalFilename());

        video.setFilePath(videopath);
        video.setContentType(file.getContentType());
        videoRepository.save(video);

        return modelMapper.map(video, VideoDto.class);


    }

    @Override
    public ResourceContantType getVideoResource(String videoId) {

        Video video = videoRepository.findById(videoId).orElseThrow(() -> new RuntimeException("Video not found"));
        

        return null;
    }

    @Service
    public static class CustomUserDetailService implements UserDetailsService {

        @Autowired
        UserRepo userRepo;

        @Override
        public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
            return userRepo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
        }
    }
}
