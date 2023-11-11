package com.grig.electronicstore.userservice.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DiscriminatorValue("employee")
public class Employee extends UserEntity {

    private Double salary;

    @Builder
    public Employee(Long id, String username, String email, String password, List<Role> roles, LocalDateTime registeredAt, Set<Address> addressSet) {
        super(id, username, email, password, roles, registeredAt, addressSet);
    }

}
