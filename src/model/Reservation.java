package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;
import javax.persistence.*;

@Entity
@Table(name = "RESERVATION")

@SequenceGenerator(name = "res_seq", initialValue = 1, allocationSize = 1)
@SuppressWarnings("SerializableClass")

public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "res_seq")

    private int reservation_ID;
    @Temporal(TemporalType.DATE)
    private Calendar checkinDate;
    @Temporal(TemporalType.DATE)
    private Calendar checkoutDate;
    private int numofAdults;
    private int numofChildren;
    @Temporal(TemporalType.DATE)
    private Calendar reservationDate;

    @ManyToOne()
    @JoinColumn(name = "rid")
    private Room room;

    @ManyToOne()
    @JoinColumn(name = "gid")
    private Guest gst;

    
    public Reservation(Calendar checkinDate, Calendar checkoutDate, int numofAdults, int numofChildren, Calendar reservationDate) {
        this.checkinDate = checkinDate;
        this.checkoutDate = checkoutDate;
        this.numofAdults = numofAdults;
        this.numofChildren = numofChildren;
        this.reservationDate = reservationDate;
    }

    public int getreservation_ID() {
        return reservation_ID;
    }

    public void setreservation_ID(int reservation_ID) {
        this.reservation_ID = reservation_ID;
    }

    public Calendar getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(Calendar checkinDate) {
        this.checkinDate = checkinDate;
    }

    public Calendar getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(Calendar checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public int getNumofAdults() {
        return numofAdults;
    }

    public void setNumofAdults(int numofAdults) {
        this.numofAdults = numofAdults;
    }

    public int getNumofChildren() {
        return numofChildren;
    }

    public void setNumofChildren(int numofChildren) {
        this.numofChildren = numofChildren;
    }

    public Calendar getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Calendar reservationDate) {
        this.reservationDate = reservationDate;
    }

}
