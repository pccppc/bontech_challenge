package com.bontech.intershipt.demo.models;

import com.bontech.intershipt.demo.models.usr.NormalUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@IdClass(ServiceUsesHistoryKey.class)
public class ServiceUsesHistory {

    @Id
    private Long userId;
    @Id
    private Long serviceId;
    @Id
    private Date date;

}
