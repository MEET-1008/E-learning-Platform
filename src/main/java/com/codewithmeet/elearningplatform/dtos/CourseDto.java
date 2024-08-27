package com.codewithmeet.elearningplatform.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto
{


    private String id;

    private String title;

    private String shortDesc;


    private String longDesc;

    private double price;

    private boolean live = false;

    private double discount;

    private Date createdDate;

    // add your fields

    private String banner;

    private String bannerContactType ;

//    private  String categoryId;

    //    videos

    private List<VideoDto> videos = new ArrayList<>();

    //

    private  List<CategoryDto> categoryList=new ArrayList<>();

    public String getBannerUrl(){
        return "http://localhost:8080/api/v1/courses/" + id +"/banner";
    }

}
