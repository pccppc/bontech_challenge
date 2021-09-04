package com.bontech.intershipt.demo.models;


import java.io.Serializable;
import java.util.Date;

class ServiceUsesHistoryKey implements Serializable {
    private Long userId;
    private Long serviceId;
    private Date date;
}