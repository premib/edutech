package com.nameless.edutech.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ClassPeriodInformation {
    @Id
    @GeneratedValue
    private long id;
    
    @ManyToOne
    @JoinColumn(name = "period_id")
    private Period period;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

}
