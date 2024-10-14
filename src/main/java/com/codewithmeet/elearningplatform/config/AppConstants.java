package com.codewithmeet.elearningplatform.config;


import java.io.File;

public class AppConstants {

    public static final String DEFAULT_PAGE_NUMBER = "0";
    public static final String DEFAULT_PAGE_SIZE = "10";

    public static final String DEFAULT_SORT_BY = "title";


    public static final String banner_UPLOAD_PATH = "upload" + File.separator + "courses" + File.separator + "banner";
    public static final String video_UPLOAD_PATH = "upload" + File.separator + "courses" + File.separator + "video";

    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_TEACHER = "ROLE_TEACHER";
    public static final String USER = "USER";
    public static final String ADMIN = "ADMIN";
    public static final String TEACHER = "TEACHER";
}