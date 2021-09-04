package com.bontech.intershipt.demo.models.db.usr;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AdminUser{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;

}
