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
@Table(name = "EMAIL_DATA")
@Entity
public class EmailDataDao {
    @Id
    @Column(name = "ID", unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name="USER_ID")
    Long userId;

    @Column(name="EMAIL",columnDefinition="varchar(500)", unique=true)
    String email;
}
