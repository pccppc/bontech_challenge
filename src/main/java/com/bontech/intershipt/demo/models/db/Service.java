package com.bontech.intershipt.demo.models.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Long fee;
    @OneToMany(targetEntity = ServiceActivationDate.class , fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "service")
    @JsonIgnore
    private List<ServiceActivationDate> serviceActivationDate ;
}
