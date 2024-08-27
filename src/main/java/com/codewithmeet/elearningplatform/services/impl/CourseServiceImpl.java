package com.codewithmeet.elearningplatform.services.impl;

import com.codewithmeet.elearningplatform.config.AppConstants;
import com.codewithmeet.elearningplatform.dtos.CourseDto;
import com.codewithmeet.elearningplatform.dtos.ResourceContantType;
import com.codewithmeet.elearningplatform.dtos.VideoDto;
import com.codewithmeet.elearningplatform.entities.Course;
import com.codewithmeet.elearningplatform.entities.Video;
import com.codewithmeet.elearningplatform.repositories.CategoryRepo;
import com.codewithmeet.elearningplatform.repositories.CourseRepo;
import com.codewithmeet.elearningplatform.repositories.VideoRepo;
import com.codewithmeet.elearningplatform.services.CategoryService;
import com.codewithmeet.elearningplatform.services.CourseService;
import com.codewithmeet.elearningplatform.services.FileService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepo courseRepository;
    @Autowired
    public FileService fileService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private VideoRepo videoRepo;

    @Override
    public CourseDto createCourse(CourseDto courseDto) {

        courseDto.setId(UUID.randomUUID().toString());
        courseDto.setCreatedDate(new Date());

        Course course = modelMapper.map(courseDto, Course.class);

        Course savedCourse = courseRepository.save(course);


        //String cat=courseDto.getCategoryId();

//        categoryService.addCourseToCategory(cat,savedCourse.getId());


        return modelMapper.map(savedCourse, CourseDto.class);
    }

    @Override
    public CourseDto updateCourse(String id, CourseDto courseDto) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        modelMapper.map(courseDto, course);
        //one by one
        Course updatedCourse = courseRepository.save(course);
        return modelMapper.map(updatedCourse, CourseDto.class);
    }

    @Override
    public CourseDto getCourseById(String id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        return modelMapper.map(course, CourseDto.class);
    }

    @Override
    public Page<CourseDto> getAllCourses(Pageable pageable) {
        Page<Course> courses = courseRepository.findAll(pageable);
        List<CourseDto> dtos = courses.getContent()
                .stream()
                .map(course -> modelMapper.map(course, CourseDto.class))
                .collect(Collectors.toList());

        //if you want you can create your page response

        return new PageImpl<>(dtos, pageable, courses.getTotalElements());
    }

    @Override
    public void deleteCourse(String id) {
        courseRepository.deleteById(id);
    }

    @Override
    public List<CourseDto> searchCourses(String keyword) {


//        if(title)
//        {
//
//        }else if(desc)
//        {
//
//        }
//        else if(price)
//        {
//
//        }
        List<Course> courses = courseRepository.findByTitleContainingIgnoreCaseOrShortDescContainingIgnoreCase(keyword, keyword);
        return courses.stream()
                .map(course -> modelMapper.map(course, CourseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void addVideoToCourse(String courseId, String videoId) {

        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));
        Video video = videoRepo.findById(videoId).orElseThrow(() -> new RuntimeException("Video not found"));
        course.addVideo(video);
        courseRepository.save(course);


        System.out.println("video added...!");
    }

    @Override
    @Transactional
    public List<VideoDto> getAllVideosByCourseid(String courseId) {

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        List<Video> videos = course.getVideos();

        return videos.stream().map(video -> modelMapper.map(video, VideoDto.class)).collect(Collectors.toList());
    }

    @Override
    public CourseDto saveCourseBanner(String CourseId, MultipartFile file) throws IOException {
        Course course = courseRepository.findById(CourseId).orElseThrow(() -> new RuntimeException("Course not found"));

        String filepath = fileService.savefile(file, AppConstants.banner_UPLOAD_PATH, file.getOriginalFilename());

        course.setBanner(filepath);
        course.setBannerContactType(file.getContentType());
        Course save = courseRepository.save(course);
        return modelMapper.map(save, CourseDto.class);


    }

    @Override
    public ResourceContantType getCoursebannerById(String courseId) {

        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));
        String bannerpath = course.getBanner();
        Path path = Paths.get(bannerpath);
        Resource resource = new FileSystemResource(path);
        ResourceContantType resourceContantType = new ResourceContantType();
        resourceContantType.setResource(resource);
        resourceContantType.setContactType(course.getBannerContactType());


        return resourceContantType;
    }


}
