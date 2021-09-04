package com.bontech.intershipt.demo.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DisableServiceDateModel {

    private LocalDate date;
    private Float startTime;
    private Float endTime;

}
