
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

    public Room(String room_type, int numofbeds, double rate) {
        this.room_type = room_type;
        this.numofbeds = numofbeds;
        this.rate = rate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "room_seq")
    
    private int roid;
    private String room_type;
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

    public String getRoom_type() {
        return room_type;
    }

    public void setRoom_type(String room_type) {
        this.room_type = room_type;
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

}
