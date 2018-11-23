/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.persistence.*;

@Entity
@Table(name = "GUEST")
@SequenceGenerator(name = "guest_seq", initialValue = 1, allocationSize = 1)
@SuppressWarnings("SerializableClass")

public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "guest_seq")
    
    private int guest_ID;
    private String guest_fname;
    private String guest_lname;
    private String guest_email;
    private String guest_phonenum;

    public Guest() {
    }

    public Guest(String guest_fname, String guest_lname, String guest_email, String guest_phonenum) {
        this.guest_fname = guest_fname;
        this.guest_lname = guest_lname;
        this.guest_email = guest_email;
        this.guest_phonenum = guest_phonenum;
    }

    public int getGuest_ID() {
        return guest_ID;
    }

    public void setGuest_ID(int guest_ID) {
        this.guest_ID = guest_ID;
    }

    public String getGuest_fname() {
        return guest_fname;
    }

    public void setGuest_fname(String guest_fname) {
        this.guest_fname = guest_fname;
    }

    public String getGuest_lname() {
        return guest_lname;
    }

    public void setGuest_lname(String guest_lname) {
        this.guest_lname = guest_lname;
    }

    public String getGuest_email() {
        return guest_email;
    }

    public void setGuest_email(String guest_email) {
        this.guest_email = guest_email;
    }

    public String getGuest_phonenum() {
        return guest_phonenum;
    }

    public void setGuest_phonenum(String guest_phonenum) {
        this.guest_phonenum = guest_phonenum;
    }
    
    @Override
    public String toString() {
    }


}
