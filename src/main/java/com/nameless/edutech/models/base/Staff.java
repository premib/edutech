package com.nameless.edutech.models.base;

import com.nameless.edutech.models.Classroom;
import com.nameless.edutech.models.ExternalHuman;
import com.nameless.edutech.models.Role;
import com.nameless.edutech.models.Subject;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper=true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class Staff extends Human {
    @ManyToMany
    @JoinTable(
            name = "staff_roles",
            joinColumns = @JoinColumn(name = "staff_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> role;

    @ManyToMany
    @JoinTable(
            name = "staff_external_human",
            joinColumns = @JoinColumn(name = "staff_id"),
            inverseJoinColumns = @JoinColumn(name = "external_human_id")
    )
    private List<ExternalHuman> staffGuardians = new ArrayList<>();
}
