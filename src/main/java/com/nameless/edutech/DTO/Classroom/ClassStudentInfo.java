package com.nameless.edutech.DTO.Classroom;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassStudentInfo {
    private int id;

    @JsonIgnore
    private String firstName;

    @JsonIgnore
    private String lastName;

    private String rollNumber;

    @JsonProperty("name")
    public String getName() {
        return (firstName + " " + lastName).trim();
    }
}
