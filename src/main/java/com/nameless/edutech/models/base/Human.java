package com.nameless.edutech.models.base;

import jakarta.persistence.*;
import lombok.*;

import com.nameless.edutech.models.embeddable.Contact;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

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
    private long id;

    @Column(length = 50, nullable = false)
    private String firstName;

    @Column(length = 50, nullable = false)
    private String lastName;

    private LocalDate dob;

    @Embedded
    private Contact contact;

    private String gender;

    @Column(columnDefinition = "TEXT")
    private String photoUrl;

    @Column(length = 5)
    private String bloodType;
}
