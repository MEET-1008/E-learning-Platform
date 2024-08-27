package com.codewithmeet.elearningplatform.dtos;

import com.codewithmeet.elearningplatform.entities.Course;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VideoDto
{

    private String videoId;

    private  String title;

    private  String desc;

    private  String filePath;

    private  String contentType;

    // add your choice of field

    String GetVideoUrl(){
        return "http://localhost:8080/api/v1/videos/" + videoId +"/banner";
    }




}
