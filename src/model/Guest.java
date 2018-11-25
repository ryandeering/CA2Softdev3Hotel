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
    private String fname;
    private String lname;
    private String email;
    private String phonenum; 

    public Guest() {
    }

    public Guest(String fname, String lname, String email, String phonenum) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.phonenum = phonenum;
    }

    public int getGuest_ID() {
        return guest_ID;
    }

    public void setGuest_ID(int guest_ID) {
        this.guest_ID = guest_ID;
    }

    public String getfname() {
        return fname;
    }

    public void setfname(String fname) {
        this.fname = fname;
    }

    public String getlname() {
        return lname;
    }

    public void setlname(String lname) {
        this.lname = lname;
    }

    public String getemail() {
        return email;
    }

    public void setemail(String email) {
        this.email = email;
    }

    public String getphonenum() {
        return phonenum;
    }

    public void setphonenum(String phonenum) {
        this.phonenum = phonenum;
    }
    
    @Override
    public String toString() {
        return"a";
    }
    


}
