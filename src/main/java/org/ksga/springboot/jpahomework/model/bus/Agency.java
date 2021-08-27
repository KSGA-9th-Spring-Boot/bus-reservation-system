package org.ksga.springboot.jpahomework.model.bus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.ksga.springboot.jpahomework.model.user.User;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Getter
@Setter
@ToString
@Table(name = "agency",
        indexes = @Index(
                name = "idx_agency_code",
                columnList = "code",
                unique = true
        ))
@Entity
public class Agency extends BaseEntity {
    private String code;

    private String name;

    private String details;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_user_id")
    @ToString.Exclude
    private User owner;

    @OneToMany(mappedBy = "agency", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<Bus> buses;
}