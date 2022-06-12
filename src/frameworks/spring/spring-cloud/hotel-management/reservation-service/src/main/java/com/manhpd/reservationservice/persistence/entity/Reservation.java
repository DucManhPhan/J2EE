package com.manhpd.reservationservice.persistence.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="RESERVATION")
public class Reservation {

    @Id
    @Column(name = "RESERVATION_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "ROOM_ID")
    private long roomId;

    @Column(name = "GUEST_ID")
    private long guestId;

    @Column(name = "RES_DATE")
    private Date date;

}
