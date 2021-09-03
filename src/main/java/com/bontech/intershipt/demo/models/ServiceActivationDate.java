package com.bontech.intershipt.demo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class ServiceActivationDate{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate date;
    private Float startTime;
    private Float endTime;
    private Integer maximumNumberOfUses;
    @ManyToOne(targetEntity = Service.class)
    @JoinColumn(columnDefinition = "service_id")
    private Service service;
}
