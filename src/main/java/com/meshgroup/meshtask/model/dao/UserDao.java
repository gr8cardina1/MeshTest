package com.meshgroup.meshtask.model.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(toBuilder = true)
@Table(name = "\"USER\"")
@Entity
public class UserDao {
    @Id
    @Column(name = "ID", unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "NAME", columnDefinition = "varchar(500)", nullable = false)
    String name;

    @Column(name = "DATE_OF_BIRTH", columnDefinition = "varchar(10)")
    String dateOfBirth;

    @Column(name = "PASSWORD", columnDefinition = "varchar(500)", nullable = false)
    String password;
}
