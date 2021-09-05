package com.bontech.intershipt.demo.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class UserIdServiceIdModel {
    private Long userId;
    private Long serviceId;
}
