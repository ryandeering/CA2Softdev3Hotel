
package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;
import javax.persistence.*;

@Entity
@Table(name = "ROOM")

@SequenceGenerator(name = "room_seq", initialValue = 1, allocationSize = 1)
@SuppressWarnings("SerializableClass")

public class Room {

 
    
    
    public Room() {
    }

    public Room(String roomtier, int numofbeds, double rate) {
        this.roomtier = roomtier;
        this.numofbeds = numofbeds;
        this.rate = rate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "room_seq")
    
   private int roid;
    private String roomtier;
    private int numofbeds;
    private double rate;
    
     @OneToMany(mappedBy = "room",cascade = CascadeType.ALL)
    private List<Reservation> rlist = new ArrayList<>();
    

    public int getRoid() {
        return roid;
    }

    public void setRoid(int roid) {
        this.roid = roid; 
    }

    public String getroomtier() {
        return roomtier;
    }

    public void setroomtier(String roomtier) {
        this.roomtier = roomtier;
    }

    public int getNumofbeds() {
        return numofbeds;
    }

    public void setNumofbeds(int numofbeds) {
        this.numofbeds = numofbeds;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Room ID/Number: " + roid + "Tier: " + roomtier + "Number of beds: " + numofbeds + "Rate: " + rate;
    }

    
    
}
