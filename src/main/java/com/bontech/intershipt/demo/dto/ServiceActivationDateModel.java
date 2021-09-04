package com.bontech.intershipt.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ServiceActivationDateModel {

    private LocalDate date;
    private Float startTime;
    private Float endTime;
    private Integer maximumNumberOfUses;

}
