package com.grig.electronicstore.userservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "addressSet")
@EqualsAndHashCode(exclude = "addressSet")
@Entity
@Table(name = "user_data")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Embedded
    @AttributeOverride(name = "phone", column = @Column(name = "phone_number", unique = true))
    private PersonalInfo personalInfo;

    @Enumerated(EnumType.STRING)
    private Role role;

    private LocalDateTime registeredAt;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Address> addressSet = new HashSet<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Payment> paymentList = new ArrayList<>();

    public void addAddress(Address address) {
        addressSet.add(address);
        address.setUser(this);
    }

    public void addPayment(Payment payment) {
        paymentList.add(payment);
        payment.setUser(this);
    }

}
