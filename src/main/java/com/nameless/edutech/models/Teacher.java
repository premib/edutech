package com.nameless.edutech.models;

import com.nameless.edutech.models.base.Staff;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper=true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Teacher extends Staff {
    @OneToOne(mappedBy = "inchargeStaff")
    private Classroom classroom;

    @ManyToMany
    @JoinTable(
            name = "staff_subject",
            joinColumns = @JoinColumn(name = "staff_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private List<Subject> subjects;
}
