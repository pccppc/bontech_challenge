package com.bontech.intershipt.demo.models.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ServiceModel {

    private String name;
    private Long fee;

}
