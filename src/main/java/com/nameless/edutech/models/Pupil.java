package com.nameless.edutech.models;

import com.nameless.edutech.models.base.Human;
import com.nameless.edutech.models.embeddable.Address;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@EqualsAndHashCode(callSuper=true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Pupil extends Human {
    @Column(length = 50)
    private String firstName;

    @Column(length = 50)
    private String lastName;

    private Date dob;

    @Embedded
    private Address address;

    @ManyToOne
    @JoinColumn(name = "classroom_id")
    private Classroom classroom;
}
