package com.codewithmeet.elearningplatform.repositories;


import com.codewithmeet.elearningplatform.entities.Course;
import com.codewithmeet.elearningplatform.entities.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VideoRepo extends JpaRepository<Video,String> {

    Optional<Video> findByTitle(String title);

    List<Video> findByCourse(Course course);

    List<Video> findByTitleContainingIgnoreCaseOrDescContainingIgnoreCase(String keyword, String keyword1);
}
