package com.bontech.intershipt.demo.dto;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ServiceActivationDateModel {

    private LocalDate date;
    private Float startTime;
    private Float endTime;
    private Integer maximumNumberOfUses;

}
