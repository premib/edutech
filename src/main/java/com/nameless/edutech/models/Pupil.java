package com.nameless.edutech.models;

import com.nameless.edutech.models.base.Human;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper=true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class Pupil extends Human {
    @ManyToOne
    @JoinColumn(name = "classroom_id")
    private Classroom classroom;
}
