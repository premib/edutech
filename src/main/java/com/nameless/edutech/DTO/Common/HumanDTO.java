package com.nameless.edutech.DTO.Common;

import com.nameless.edutech.models.embeddable.Contact;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HumanDTO {
    private long id;

    private String firstName;

    private String lastName;

    private LocalDate dob;

    private Contact contact;

    private String gender;

    private String photoUrl;

    private String bloodType;
}
