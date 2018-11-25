/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "BILLING")

@SequenceGenerator(name = "bill_seq", initialValue = 1, allocationSize = 1)
@SuppressWarnings("SerializableClass")

public class Billing {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bill_seq")

    private int billing_ID;
    private double initial_charges;
    private double misc_charges;
    private Calendar pay_date;

    public Billing() {
    }

    public Billing(double initial_charges, double misc_charges, Calendar pay_date) {
        this.initial_charges = initial_charges;
        this.misc_charges = misc_charges;
        this.pay_date = pay_date;
    }

    public int getBilling_ID() {
        return billing_ID;
    }

    public void setBilling_ID(int billing_ID) {
        this.billing_ID = billing_ID;
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
