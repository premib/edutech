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
public class Staff extends Human {
    @Column(nullable = false, length = 50)
    private String firstName;

    @Column(nullable = false, length = 50)
    private String lastName;

    private Date dob;

    @Embedded
    private Address address;

    @Column(columnDefinition = "TEXT")
    private String photoUrl;

    @OneToOne(mappedBy = "inchargeStaff")
    private Classroom classroom;
}
