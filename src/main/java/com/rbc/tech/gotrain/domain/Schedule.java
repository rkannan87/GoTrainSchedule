package com.rbc.tech.gotrain.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@Entity
public class Schedule {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    Integer id;
    String line;
    String departure;
    String arrival;

    public Schedule() {}

}