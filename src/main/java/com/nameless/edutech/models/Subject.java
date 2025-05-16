package com.nameless.edutech.models;


import com.nameless.edutech.models.base.Staff;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Subject {
    @Id
    @GeneratedValue
    private long id;

    private String code;

    private String name;

    private String description;

    private String department;

    private boolean active = true;

    @ManyToMany(mappedBy = "subjects")
    private List<Staff> staffList;
}
