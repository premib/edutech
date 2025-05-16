package com.nameless.edutech.models;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Where;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.commons.lang3.BooleanUtils.TRUE;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
//@Where(clause = "is_admin_role = true")
public class Role {
    private static final Pattern CAMEL_REGEX = Pattern.compile("\\s|(?<=[a-z])(?=[A-Z])|(?<=[A-Z])(?=[A-Z][a-z])");

    @Id
    private String id;

    @Column(unique=true, nullable=false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "BOOLEAN", name = "is_admin_role")
    @ColumnDefault(value = "false")
    private boolean isAdminRole;

    @PrePersist
    private void ensureRoleGenerated() {
        if (id == null && name != null) {
            Matcher matcher = CAMEL_REGEX.matcher(name);
            this.id = matcher.replaceAll("_").toLowerCase();
        }
    }
}
