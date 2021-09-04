package com.bontech.intershipt.demo.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class ServiceActivationDate{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate date;
    private Float startTime;
    private Float endTime;
    private Integer maximumNumberOfUses;
    private Boolean isActive;
    @ManyToOne(targetEntity = Service.class)
    @JoinColumn(columnDefinition = "service_id")
    private Service service;
}
