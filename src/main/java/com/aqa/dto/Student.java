package com.aqa.dto;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

@Entity
@Table(name = "student")
@Data
@Accessors(chain = true)
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "groupNumber")
    private int groupNumber;
//    Hibernate annotations here
//    hibernate.cfg.xml file contains all params for Student class (connection to DB)
}