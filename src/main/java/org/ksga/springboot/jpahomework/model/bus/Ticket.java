package org.ksga.springboot.jpahomework.model.bus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.ksga.springboot.jpahomework.model.user.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@Table(name = "ticket")
@Entity
public class Ticket extends BaseEntity {
    @Column(name = "seat_number")
    private int seatNumber;

    private Boolean cancellable;

    @Column(name = "journey_date")
    private String journeyDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_schedule_id")
    private TripSchedule tripSchedule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User passenger;
}
