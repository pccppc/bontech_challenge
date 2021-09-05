package com.bontech.intershipt.demo.models.db;

import com.bontech.intershipt.demo.models.db.usr.NormalUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user_service")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NormalUserService {

    @EmbeddedId
    private NormalUserServiceId id;

    @ManyToOne
    @MapsId("service_id")
    private Service service;

    @ManyToOne
    @MapsId("user_id")
    private NormalUser user;

    private Integer numberOfUsage = 0;
}
