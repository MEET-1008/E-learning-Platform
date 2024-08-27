package com.codewithmeet.elearningplatform.controllers;

import com.codewithmeet.elearningplatform.dtos.CourseDto;
import com.codewithmeet.elearningplatform.dtos.CustomMessage;
import com.codewithmeet.elearningplatform.dtos.ResourceContantType;
import com.codewithmeet.elearningplatform.dtos.VideoDto;
import com.codewithmeet.elearningplatform.services.CourseService;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }


    @PostMapping
    public ResponseEntity<CourseDto> createCourse(@RequestBody CourseDto courseDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.createCourse(courseDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDto> updateCourse(@PathVariable String id, @RequestBody CourseDto courseDto) {
        return ResponseEntity.ok(courseService.updateCourse(id, courseDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDto> getCourseById(@PathVariable String id) {
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    @GetMapping
    public ResponseEntity<Page<CourseDto>> getAllCourses(Pageable pageable) {
        return ResponseEntity.ok(courseService.getAllCourses(pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable String id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<CourseDto>> searchCourses(
            @RequestParam String keyword) {
        System.out.println("searching element");
        return ResponseEntity.ok(courseService.searchCourses(keyword));
    }


    @PostMapping("/{courseId}/videos/{videoId}")
    public ResponseEntity<CustomMessage> addVideoToCourse(
            @PathVariable String courseId,
            @PathVariable String videoId) {

        courseService.addVideoToCourse(courseId, videoId);
        CustomMessage message = new CustomMessage();
        message.setMessage("Added video to course");
        message.setSuccess(true);

        return ResponseEntity.ok(message);
    }


    @GetMapping("/{courseId}/videos")
    public ResponseEntity<List<VideoDto>> getAllVideos(
            @PathVariable String courseId) {

        return ResponseEntity.ok(courseService.getAllVideosByCourseid(courseId));

    }


    @PostMapping("/{courseId}/banner")
    public ResponseEntity<?> addBannerToCourse(
            @PathVariable String courseId,
            @RequestParam("banner") MultipartFile banner) throws IOException {

        String contentType = banner.getContentType();
        System.out.println(contentType + "meet");
        if (!contentType.equalsIgnoreCase("image/jpeg") && !contentType.equalsIgnoreCase("image/png")) {
            CustomMessage message = new CustomMessage();
            message.setMessage("file type invalid... ! , only jpg , jpeg , png , gif file valid !!");
            message.setSuccess(true);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }

        CourseDto courseDto = courseService.saveCourseBanner(courseId, banner);


        return ResponseEntity.ok(courseDto);

    }


    @GetMapping("/{courseId}/banner")
    public ResponseEntity<Resource> getBannerOfCourse(
            @PathVariable String courseId) {

        ResourceContantType resourceContantType = courseService.getCoursebannerById(courseId);

        return ResponseEntity
                .ok()
                .contentType(MediaType
                        .parseMediaType(resourceContantType
                                .getContactType()))
                .body(resourceContantType.getResource());

    }

}
