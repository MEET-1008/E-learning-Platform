package com.codewithmeet.elearningplatform.entities;


import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "courses")
@Data
public class Course {

     @Id
    private String id;

    private String title;

    private String shortDesc;

    @Column(length = 2000)
    private String longDesc;

    private double price;

    private boolean live = false;

    private double discount;

    private Date createdDate;

    // add your fields

    private String banner;

    public String bannerContactType;


    //
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Category> categoryList = new ArrayList<>();

    //    videos
    @OneToMany(mappedBy = "course")
    private List<Video> videos = new ArrayList<>();

    public void addCategory(Category category) {
        categoryList.add(category);
        category.getCourses().add(this);
    }

    public void removeCategory(Category category) {
        categoryList.remove(category);
        category.getCourses().remove(this);
    }

    public void addVideo(Video video) {
        videos.add(video);
        video.setCourse(this);
    }


}
