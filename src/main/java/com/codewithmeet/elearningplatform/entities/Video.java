package com.codewithmeet.elearningplatform.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Video {

    @Id
    private String videoId;

    private String title;

    @Column(name = "description", length = 1000)
    private String desc;

    private String filePath;

    private String contentType;

    // add your choice of field

    @ManyToOne
    private  Course course;

}
