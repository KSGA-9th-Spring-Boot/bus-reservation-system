package org.ksga.springboot.jpahomework.model.bus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.time.Instant;

@Getter
@Setter
@ToString
@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(generator = "custom-generator", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "custom-generator", strategy = "org.ksga.springboot.jpahomework.model.id_generator.BaseIdentifierGenerator")
    protected String id;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    protected Instant createdAt;

    @CreationTimestamp
    @Column(name = "updated_at", updatable = false, nullable = false)
    protected Instant updatedAt;

    @Column
    @Version
    protected int version;
}
