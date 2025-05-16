package com.nameless.edutech.models;

import com.nameless.edutech.models.base.Audit;
import com.nameless.edutech.models.base.Staff;
import com.nameless.edutech.models.enums.Theme;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper=true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SuperBuilder
@Table(name = "AppUser")
public class User extends Audit {
    @Id
    private String username;

    @Column(nullable = false, unique = true)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column
    private Theme theme;

    @OneToOne
    @JoinColumn(name = "staff_id")
    private Staff staff;
}
