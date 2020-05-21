package com.iss.icare.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse {

    private Long id;
    private String name;

    public ApiResponse(String name, Long id) {
        this.id = id;
        this.name = name;
    }

    public ApiResponse(String name) {

        this.name = name;
    }

}
