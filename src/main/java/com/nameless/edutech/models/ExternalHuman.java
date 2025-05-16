package com.nameless.edutech.models;

import com.nameless.edutech.models.base.Human;
import com.nameless.edutech.models.base.Staff;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ExternalHuman extends Human {
    @ManyToMany(mappedBy = "studentGuardians")
    private List<Student> relatedStudents;

    @ManyToMany(mappedBy = "staffGuardians")
    private List<Staff> relatedStaffs;
}
