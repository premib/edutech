package com.nameless.edutech.models;

import com.nameless.edutech.models.base.Human;
import com.nameless.edutech.models.embeddable.Address;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.Date;

@EqualsAndHashCode(callSuper=true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class Staff extends Human {

    private String role;

    @Column(columnDefinition = "TEXT")
    private String photoUrl;

    @OneToOne(mappedBy = "inchargeStaff")
    private Classroom classroom;
}
