package model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "GUEST")
@SequenceGenerator(name = "guest_seq", initialValue = 1, allocationSize = 1)
@SuppressWarnings("SerializableClass")

public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "guest_seq")
    private int gid;
    private String fname;
    private String lname;
    private String email;
    private String phonenum;

    @OneToMany(mappedBy = "gst", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> rlist = new ArrayList<>();

    @OneToMany(mappedBy = "gst", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Billing> blist = new ArrayList<>();

    public Guest() {
    }

    public Guest(String fname, String lname, String email, String phonenum) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.phonenum = phonenum;
    }

   
    
    
    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
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
        String s = String.format("Guest ID: (" + gid + ") Name:" + fname + " " + lname + " Email:" + email + " Phonenum:" + phonenum);

        return s;
    }

    public void printReservations() {
        System.out.println("Reservations for: " + fname + " " + lname);
        for (int i = 0; i < rlist.size(); i++) {
            System.out.println(rlist.get(i));
        }

    }
    
        public void printBillings() {
        System.out.println("Billings for: " + fname + " " + lname);
        for (int i = 0; i < blist.size(); i++) {
            System.out.println(blist.get(i));
        }

    }
    
    

    public List<Reservation> getRlist() {
        return rlist;
    }

    public List<Billing> getBlist() {
        return blist;
    }

}
