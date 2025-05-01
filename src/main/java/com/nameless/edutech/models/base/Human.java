package com.nameless.edutech.models.base;

import jakarta.persistence.*;
import lombok.*;

import com.nameless.edutech.models.embeddable.Address;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Human extends Audit {
    @Id
    private long id;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String email;

    private String phone;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;
}
