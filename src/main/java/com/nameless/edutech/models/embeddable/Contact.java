package com.nameless.edutech.models.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class Contact {

    @Column(unique = true)
    protected String email;

    protected String phone;

    private String address;

    private String city;

    private String state;

    private String zip;

    private String country;
}
