package com.nameless.edutech.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Period {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String periodName;

    private LocalTime startTime;

    private LocalTime endTime;

    private boolean isSpecial = false;
}
