package com.meshgroup.meshtask.model.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(toBuilder = true)
@Table(name = "ACCOUNT")
@Entity
public class AccountDao {
    @Id
    @Column(name = "ID", unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "USER_ID")
    Long userId;

    @Column(name = "BALANCE",columnDefinition = "decimal(30,2)")
    BigDecimal balance;
}
