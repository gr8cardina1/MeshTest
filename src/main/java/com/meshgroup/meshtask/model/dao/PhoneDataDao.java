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
@Table(name = "PHONE_DATA")
@Entity
public class PhoneDataDao {
    @Id
    @Column(name = "ID", unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name="USER_ID")
    Long userId;

    @Column(name="PHONE",columnDefinition="varchar(13)", unique=true)
    String phone;
}
