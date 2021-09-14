package org.ksga.springboot.jpahomework.model.bus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@Table(
        name = "stop",
        indexes = @Index(
                name = "idx_stop_code",
                columnList = "code",
                unique = true
        ))
@Entity
public class Stop extends BaseEntity {
    private String id;
    private String code;
    private String name;
    private String detail;
}
