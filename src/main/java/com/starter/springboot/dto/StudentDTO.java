package com.starter.springboot.dto;

import lombok.Data;

// @Data creates all the constructors and getter and setter for the present class
// Removes all the boiler code
@Data
public class StudentDTO {

    private Long id;
    private String name;
    private String email;

    public StudentDTO(long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
