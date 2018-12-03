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
    private double initial_charges;
    private double misc_charges;
    @Temporal(TemporalType.DATE)
    private Calendar pay_date;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn(name = "bid")
    private Guest gst;

    public Billing() {
    }

    public Billing(double initial_charges, double misc_charges, Calendar pay_date) {
        this.initial_charges = initial_charges;
        this.misc_charges = misc_charges;
        this.pay_date = pay_date;
    }

    public int getBid() {
        return bid;
    }

    public void setBilling_ID(int bid) {
        this.bid = bid;
    }

    public double getInitial_charges() {
        return initial_charges;
    }

    public void setInitial_charges(double initial_charges) {
        this.initial_charges = initial_charges;
    }

    public double getMisc_charges() {
        return misc_charges;
    }

    public void setMisc_charges(double misc_charges) {
        this.misc_charges = misc_charges;
    }

    public Calendar getPay_date() {
        return pay_date;
    }

    public void setPay_date(Calendar pay_date) {
        this.pay_date = pay_date;
    }

}
