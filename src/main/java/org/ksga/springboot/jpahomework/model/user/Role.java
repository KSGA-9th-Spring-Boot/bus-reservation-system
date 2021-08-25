package org.ksga.springboot.jpahomework.model.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.ksga.springboot.jpahomework.model.bus.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "role")
public class Role extends BaseEntity {
    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    private UserRoles role;
}