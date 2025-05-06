package com.nameless.edutech.models.base;

import jakarta.persistence.*;
import lombok.*;

import com.nameless.edutech.models.embeddable.Address;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Human extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    protected long id;

    @Column(length = 50, nullable = false)
    protected String firstName;

    @Column(length = 50, nullable = false)
    protected String lastName;

    @Column(unique = true)
    protected String email;

    protected String phone;

    protected LocalDate dob;

    @Embedded
    protected Address address;
}
