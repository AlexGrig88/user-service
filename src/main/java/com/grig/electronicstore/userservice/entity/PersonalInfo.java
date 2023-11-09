package com.grig.electronicstore.userservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonalInfo {

    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate birthDate;
    @Column(unique = true)
    private String phone;
    private String avatar;

}
