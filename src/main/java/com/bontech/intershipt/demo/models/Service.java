package com.bontech.intershipt.demo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Long fee;
    private Integer maximumNumberOfUses;
    @OneToMany(targetEntity = ServiceActivationDate.class , fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "service")
    private List<ServiceActivationDate> serviceActivationDate ;
}
