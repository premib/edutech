package com.nameless.edutech.models;

import com.nameless.edutech.models.base.Audit;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper=true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Classroom extends Audit {
    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true)
    private int classNumber;

    @Column(length = 5)
    private String section;

    @OneToOne
    @JoinColumn(name = "staff_id")
    private Staff inchargeStaff;

    @OneToMany(mappedBy = "classroom")
    private List<Pupil> students;
}
