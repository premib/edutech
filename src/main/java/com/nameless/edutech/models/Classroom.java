package com.nameless.edutech.models;

import com.nameless.edutech.models.base.Audit;
import com.nameless.edutech.models.base.Staff;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper=true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"class_number", "section"}))
public class Classroom extends Audit {
    @Id
    @GeneratedValue
    private int id;

    private String classNumber;

    @Column(length = 5)
    private String section;

    @OneToOne
    @JoinColumn(name = "staff_id")
    private Staff inchargeStaff;

    @OneToMany(mappedBy = "classroom")
    private List<Student> students;

    @OneToMany
    @JoinColumn(name = "class_time_table_id")
    private List<ClassTimeTable> classTimeTable;
}
