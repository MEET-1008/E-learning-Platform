package com.codewithmeet.elearningplatform.dtos;

import lombok.Data;
import org.springframework.core.io.Resource;

@Data
public class ResourceContantType {

    private Resource resource;
    private String ContactType;

}
