package com.bontech.intershipt.demo.models;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class NormalUserServiceId implements Serializable {

    private Long serviceId;
    private Long userId;
}
