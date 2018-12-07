package model;

import java.util.Calendar;
import javax.persistence.*;

@Entity
@Table(name = "BILLING")

@SequenceGenerator(name = "bill_seq", initialValue = 1, allocationSize = 1)
@SuppressWarnings("SerializableClass")

public class Billing {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bill_seq")

    private int bid;
    private double initialcharges;
    private double misccharges;
    @Temporal(TemporalType.DATE)
    private Calendar paydate;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn(name = "bid")
    private Guest gst;

    public Billing() {
    }

    public Billing(double initialcharges, double misccharges, Calendar paydate) {
        this.initialcharges = initialcharges;
        this.misccharges = misccharges;
        this.paydate = paydate; // jesus christ i hate oracle's reserved words i can't even use DATE
    }

    public int getBid() {
        return bid;
    }

    public void setBilling_ID(int bid) {
        this.bid = bid;
    }

    public double getInitialcharges() {
        return initialcharges;
    }

    public void setInitialcharges(double initial_charges) {
        this.initialcharges = initial_charges;
    }

    public double getMisccharges() {
        return misccharges;
    }

    public void setMisccharges(double misccharges) {
        this.misccharges = misccharges;
    }

    public Calendar getPaydate() {
        return paydate;
    }

    public void setPaydate(Calendar paydate) {
        this.paydate = paydate;
    }
    
          @Override
    public String toString() {

        return String.format("Billing ID: " + bid
                + " Initial charges: " + initialcharges + " "
                + " Misc charges: " + misccharges + " "
                + " Pay date: " + dateFormat(paydate));}

    public String dateFormat(Calendar date) {
        String s = String.format("%1$td %1$tb %1$tY", date);
        return s;
    }


}
