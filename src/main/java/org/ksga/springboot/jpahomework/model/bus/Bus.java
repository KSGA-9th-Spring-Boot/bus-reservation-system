package org.ksga.springboot.jpahomework.model.bus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@Table(
        name = "bus",
        indexes = @Index(
                name = "idx_bus_code",
                columnList = "code",
                unique = true
        )
)
@Entity
public class Bus extends BaseEntity {
    private String code;

    private int capacity;

    private String make;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agency_id")
    @ToString.Exclude
    private Agency agency;
}