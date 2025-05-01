package com.nameless.edutech.models;

import com.nameless.edutech.models.base.Audit;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper=true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "AppUser")
public class User extends Audit {
    @Id
    private String username;

    @Column(nullable = false, unique = true)
    private String password;

    @OneToOne
    @JoinColumn(name = "staff_id")
    private Staff staff;
}
