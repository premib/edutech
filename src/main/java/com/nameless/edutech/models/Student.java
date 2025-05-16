package com.nameless.edutech.models;

import com.nameless.edutech.models.base.Human;
import com.nameless.edutech.models.enums.ActivityStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper=true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class Student extends Human {
    @ManyToOne
    @JoinColumn(name = "classroom_id")
    private Classroom classroom;

    @Column(nullable = false)
    private String admissionNumber;

    private LocalDate admissionDate;

    private int rollNumber;

    @ManyToMany
    @JoinTable(
            name = "student_external_human",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "external_human_id")
    )
    private List<ExternalHuman> studentGuardians;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ActivityStatus activityStatus;
}
