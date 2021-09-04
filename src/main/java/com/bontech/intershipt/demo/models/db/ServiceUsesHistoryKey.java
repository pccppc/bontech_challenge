package com.bontech.intershipt.demo.models.db;


import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode
class ServiceUsesHistoryKey implements Serializable {
    private Long userId;
    private Long serviceId;
    private Date date;
}