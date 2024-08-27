package com.codewithmeet.elearningplatform.services;

import com.codewithmeet.elearningplatform.dtos.CourseDto;
import com.codewithmeet.elearningplatform.dtos.ResourceContantType;
import com.codewithmeet.elearningplatform.dtos.VideoDto;
import com.codewithmeet.elearningplatform.entities.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CourseService {
    CourseDto createCourse(CourseDto courseDto);

    CourseDto updateCourse(String id, CourseDto courseDto);

    CourseDto getCourseById(String id);

    Page<CourseDto> getAllCourses(Pageable pageable);

    void deleteCourse(String id);

    List<CourseDto> searchCourses(String keyword);

    void addVideoToCourse(String courseId, String videoId);

    List<VideoDto> getAllVideosByCourseid(String courseId);

    CourseDto saveCourseBanner(String CourseId, MultipartFile file) throws IOException;


    ResourceContantType getCoursebannerById(String courseId);
}
