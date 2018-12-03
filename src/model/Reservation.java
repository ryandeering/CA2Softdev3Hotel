package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;
import javax.persistence.*;
import java.util.Date;
import java.util.GregorianCalendar;

@Entity
@Table(name = "RESERVATION")

@SequenceGenerator(name = "res_seq", initialValue = 1, allocationSize = 1)
@SuppressWarnings("SerializableClass")

public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "res_seq")
    private int rid;
    @Temporal(TemporalType.DATE)
    private Calendar cidate;
    @Temporal(TemporalType.DATE)
    private Calendar codate;
    private int numofAdults;
    private int numofChildren;
    @Temporal(TemporalType.DATE)
    private Calendar reservationDate;

    @ManyToOne()
    @JoinColumn(name = "roid")
    private Room room;

    @ManyToOne()
    @JoinColumn(name = "gid")
    private Guest gst;

    public Reservation(Calendar cidate, Calendar codate, int numofAdults, int numofChildren, Calendar reservationDate) {
        this.cidate = cidate;
        this.codate = codate;
        this.numofAdults = numofAdults;
        this.numofChildren = numofChildren;
        this.reservationDate = reservationDate;
    }

    public Reservation() {
    }

    public int getRid() { //lol
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public Calendar getcidate() {
        return cidate;
    }

    public void setcidate(Calendar cidate) {
        this.cidate = cidate;
    }

    public Calendar getcodate() {
        return codate;
    }

    public void setcodate(Calendar codate) {
        this.codate = codate;
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

    @Override
    public String toString() {

        return String.format("Reservation ID: " + rid
                + " Check-In Date: " + dateFormat(cidate) + " "
                + " Check-Out Date: " + dateFormat(codate) + " "
                + " Number of adults: " + numofAdults + " "
                + " Number of children: " + numofChildren + " "
                + " Reservation Date: " + dateFormat(reservationDate));
    }

    public String dateFormat(Calendar date) { // would it even be programming if you weren't doing stupid hacks?
        String s = String.format("%1$td %1$tb %1$tY", date);
        return s;
    }

}
