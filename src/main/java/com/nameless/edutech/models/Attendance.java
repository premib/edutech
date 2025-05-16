package com.nameless.edutech.models;

import com.nameless.edutech.models.base.Audit;
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
public class Attendance extends Audit {
    @Id
    private long id;

    @ManyToOne
    @JoinColumn(name = "classroom_id")
    private Classroom classroom;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Staff staff;

    @ManyToOne
    @JoinColumn(name = "period_id")
    private Period period;

    @OneToMany(mappedBy = "attendance", cascade = CascadeType.ALL)
    private List<AttendanceRecord> records;
}
