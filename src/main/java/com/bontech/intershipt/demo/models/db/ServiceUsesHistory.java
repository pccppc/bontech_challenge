package com.bontech.intershipt.demo.models.db;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@IdClass(ServiceUsesHistoryKey.class)
public class ServiceUsesHistory {

    @Id
    private Long userId;
    @Id
    private Long serviceId;
    @Id
    private Date date;

}
