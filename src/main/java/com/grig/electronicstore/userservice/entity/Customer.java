package com.grig.electronicstore.userservice.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true, exclude = { "paymentList", "numberOfBonuses" })
@ToString(exclude = "paymentList")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue("customer")
public class Customer extends UserEntity {

    private Integer numberOfBonuses;

    @Builder
    public Customer(Long id, String username, String email, String password, List<Role> roles, LocalDateTime registeredAt, Set<Address> addressSet, List<Payment> paymentList) {
        super(id, username, email, password, roles, registeredAt, addressSet);
        this.paymentList = paymentList;
    }

    @OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Payment> paymentList = new ArrayList<>();


    public void addPayment(Payment payment) {
        paymentList.add(payment);
        payment.setUserEntity(this);
    }

}
