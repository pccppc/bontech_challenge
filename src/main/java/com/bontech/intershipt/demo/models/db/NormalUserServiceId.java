package com.bontech.intershipt.demo.models.db;

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

    private Long service_id;
    private Long user_id;
}
