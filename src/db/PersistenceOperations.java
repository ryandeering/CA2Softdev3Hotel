package db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.persistence.*;
import java.util.Date;
import java.util.regex.*;
import model.*;

public class PersistenceOperations {

    ReservationOperations ro = new ReservationOperations();
    
    EntityManagerFactory emf;
    EntityManager em;
    private Connection conn;
    private PreparedStatement pstmt;

    public PersistenceOperations() {
        emf = Persistence.createEntityManagerFactory("CA2Softdev3HotelPU");
        em = emf.createEntityManager();
        
        ro.openDB();
        
        
    }

    public void addBilling(double initialcharges, double misccharges, Calendar paydate) {
        em.getTransaction().begin();
        Billing b = new Billing();
        b.setInitialcharges(initialcharges);
        b.setMisccharges(misccharges);
        b.setPaydate(paydate);
        em.persist(b);
        em.getTransaction().commit();
    }

    public void addGuest(String fname, String lname, String email, String phonenum) {
        em.getTransaction().begin();
        Guest g = new Guest();
        g.setemail(email);
        g.setfname(fname);
        g.setlname(lname);
        g.setphonenum(phonenum);
        em.persist(g);
        em.getTransaction().commit();
    }

    public void viewBilling() {
        em.getTransaction().begin();

        TypedQuery<Billing> query
                = em.createQuery("SELECT b FROM Billing b order by b.bid",
                        Billing.class);
        List<Billing> results = query.getResultList();

        for (Billing b : results) {
            System.out.println(b);
        }
        em.getTransaction().commit();
    }

    public void viewGuest() {
        em.getTransaction().begin();

        TypedQuery<Guest> query
                = em.createQuery("SELECT g FROM Guest g order by g.gid",
                        Guest.class);
        List<Guest> results = query.getResultList();

        for (Guest g : results) {
            System.out.println(g);
        }
        em.getTransaction().commit();
    }

    public void viewCurrentGuestsToday() {
        em.getTransaction().begin();
        String todaysDate = new SimpleDateFormat("dd MMM YYYY").format(new Date());
        System.out.println("--------Finding Guests who are Checking in today : " + String.valueOf(todaysDate) + "--------");

        TypedQuery<Guest> query
                = em.createQuery("SELECT g FROM Guest g order by g.gid",
                        Guest.class);
        List<Guest> results = query.getResultList();

        for (Guest g : results) {
            Matcher m = Pattern.compile("\\(([^)]+)\\)").matcher(String.valueOf(g));
            while (m.find()) {
                TypedQuery<Reservation> query3
                        = em.createQuery("Select r from Reservation r where r.gst.gid = " + String.valueOf(m.group(1)),
                                Reservation.class);
                List<Reservation> results3 = query3.getResultList();
                for (Reservation n : results3) {
                    Matcher ms = Pattern.compile("\\(([^)]+)\\)").matcher(String.valueOf(n));
                    while (ms.find()) {
                        if (String.valueOf(todaysDate).equals(String.valueOf(ms.group(1)))) {
                            System.out.println(n);
                        }
                    }
                }
            }
        }
        System.out.println("--------If Above ^^ is empty then there are no guests checking in today : " + String.valueOf(todaysDate) + "--------");
        em.getTransaction().commit();
    }

    public void viewCurrentGuestsByDate(String date) {
        em.getTransaction().begin();

        System.out.println("--------Finding Guests who are Checking in today : " + String.valueOf(date) + "--------");

        TypedQuery<Guest> query
                = em.createQuery("SELECT g FROM Guest g order by g.gid",
                        Guest.class);
        List<Guest> results = query.getResultList();

        for (Guest g : results) {
            //System.out.println("------------------------------------------------");
            //System.out.println(g);
            //System.out.println("Reservations made by the above guest are bellow");
            //System.out.println("------------------------------------------------");
            Matcher m = Pattern.compile("\\(([^)]+)\\)").matcher(String.valueOf(g));
            while (m.find()) {
                // System.out.println("Reservations made by GuestID: " + m.group(1));
                // System.out.println(" ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓ ");

                TypedQuery<Reservation> query3
                        = em.createQuery("Select r from Reservation r where r.gst.gid = " + String.valueOf(m.group(1)),
                                Reservation.class);
                List<Reservation> results3 = query3.getResultList();
                for (Reservation n : results3) {
                    Matcher ms = Pattern.compile("\\(([^)]+)\\)").matcher(String.valueOf(n));
                    while (ms.find()) {
                        if (String.valueOf(date).equals(String.valueOf(ms.group(1)))) {
                            System.out.println(n);
                        } else {

                        }
                    }

                }

            }

        }
        System.out.println("--------If Above ^^ is empty then there are no guests checking in today : " + String.valueOf(date) + "--------");
        em.getTransaction().commit();
    }

    public void viewCurrentGuestsAndRes() {
        em.getTransaction().begin();

        TypedQuery<Guest> query
                = em.createQuery("SELECT g FROM Guest g order by g.gid",
                        Guest.class);
        List<Guest> results = query.getResultList();

        for (Guest g : results) {
            System.out.println("------------------------------------------------");
            System.out.println(g);
            System.out.println("Reservations made by the above guest are bellow");
            System.out.println("------------------------------------------------");
            Matcher m = Pattern.compile("\\(([^)]+)\\)").matcher(String.valueOf(g));
            while (m.find()) {
                System.out.println("Reservations made by GuestID: " + m.group(1));
                System.out.println(" ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓ ");

                TypedQuery<Reservation> query3
                        = em.createQuery("Select r from Reservation r where r.gst.gid = " + String.valueOf(m.group(1)),
                                Reservation.class);
                List<Reservation> results3 = query3.getResultList();
                for (Reservation n : results3) {
                    System.out.println(n);
                }
            }
        }
        em.getTransaction().commit();
    }

//////////////////////////////////////////////////////////////////////////////////////////////
    public void viewGuestByName(String name) {

        em.getTransaction().begin();

        TypedQuery<Guest> query
                = em.createQuery("SELECT g FROM Guest g WHERE g.fname='" + name + "' order by g.gid",
                        Guest.class);
        List<Guest> results = query.getResultList();

        for (Guest g : results) {
            System.out.println(g);

        }
        em.getTransaction().commit();

    }

    public void addReservation(String name, String cidate, String codate, int numofAdults, int numofChildren, String reservationDate) {//this method id not working atm plz fix
        em.getTransaction().begin();
        int gid;

        TypedQuery<Guest> query
                = em.createQuery("SELECT g FROM Guest g WHERE g.fname='" + name + "' order by g.gid",
                        Guest.class);
        List<Guest> results = query.getResultList();

        for (Guest g : results) {
            System.out.println(g);
            Matcher m = Pattern.compile("\\(([^)]+)\\)").matcher(String.valueOf(g));
            while (m.find()) {
                System.out.println(name + " has a GuestID = " + m.group(1));
                gid = Integer.valueOf(m.group(1));
                
                ro.insertReservationTable(cidate, codate, numofAdults, numofChildren, reservationDate, gid);

                em.getTransaction().commit();

            }
        }

    }

////////////////////////////////////////////////////////////////////////////////////////////
    public void viewReservation() {
        em.getTransaction().begin();

        TypedQuery<Reservation> query
                = em.createQuery("SELECT r FROM Reservation r order by r.rid",
                        Reservation.class);
        List<Reservation> results = query.getResultList();

        for (Reservation r : results) {
            System.out.println(r);
        }
        em.getTransaction().commit();

    }

    public void viewReservationById(int id) {
        em.getTransaction().begin();

        TypedQuery<Reservation> query
                = em.createQuery("SELECT r FROM Reservation r WHERE r.id='" + id + "' order by r.rid",
                        Reservation.class);
        List<Reservation> results = query.getResultList();

        for (Reservation r : results) {
            System.out.println(r);
        }
        em.getTransaction().commit();

    }

    public void viewRoom() {
        em.getTransaction().begin();

        TypedQuery<Room> query
                = em.createQuery("SELECT ro FROM Room ro order by ro.roid", Room.class);
        List<Room> results = query.getResultList();

        for (Room ro : results) {
            System.out.println(ro);
        }
        em.getTransaction().commit();

    }

    public void viewGuestReservations(int id) {
        em.getTransaction().begin();
        Guest g = em.find(Guest.class, id);
        if (g == null) {
            System.out.println("Guest does not exist, please try again!");
        } else {
            g.printReservations();
        }
        em.getTransaction().commit();
    }

    public void viewGuestReservationsDate(String date) {
        System.out.println("debug: " + date);
        em.getTransaction().begin();

        String sq2 = "SELECT r FROM Reservation r WHERE r.cidate = CAST('" + date + "' AS date) order by r.cidate";

        TypedQuery<Reservation> query = em.createQuery(sq2, Reservation.class);
        List<Reservation> results = query.getResultList();

        for (Reservation r : results) {
            System.out.println(r);
        }

        em.getTransaction().commit();

    }

    public void deleteReservation(int rid, int gid) {
        Reservation r = em.find(Reservation.class, rid);
        Guest g = em.find(Guest.class, gid);
        em.getTransaction().begin();
        em.remove(r);
        g.getRlist().remove(r);
        em.getTransaction().commit();
        System.out.println("Reservation deleted.");

    }

    public Reservation findReservation(int rid) {
        Reservation r = em.find(Reservation.class, rid);
        if (r == null) {
            System.out.println("Reservation not found. Please enter only valid values.");
        }
        return r;
    }

    public Guest findGuest(int gid) {
        Guest g = em.find(Guest.class, gid);
        if (g == null) {
            System.out.println("Guest not found. Please enter only valid values.");
        }
        return g;
    }

    public void deleteGuest(int gid) {
        em.getTransaction().begin();

        String sq1 = "SELECT b FROM Billing b WHERE b.gid = " + gid;

        List<Billing> bb = em.createQuery(sq1).getResultList();
        for (Billing b : bb) {
            em.remove(b);
        }

        String sq2 = "SELECT r FROM Reservation r WHERE r.gid = " + gid;

        List<Reservation> rr = em.createQuery(sq2).getResultList();
        for (Reservation r : rr) {
            em.remove(r);
        }

        int deletedCount = em.createQuery("DELETE FROM "
                + "Guest g WHERE g.gid = " + gid).executeUpdate();

        System.out.println("entity removed");
        em.getTransaction().commit();
    }

    public void close() {
        em.close();
        emf.close();
    }

    public void viewGuestBillings(int id) {
        em.getTransaction().begin();
        Guest g = em.find(Guest.class, id);
        if (g == null) {
            System.out.println("Guest does not exist, please try again!");
        } else {
            g.printBillings();
        }
        em.getTransaction().commit();
    }

    public void updateBilling(int bid, String name, double initialcharges, double misccharges, Calendar date) {
        em.getTransaction().begin();
        Billing b = em.find(Billing.class, bid);
        b.setInitialcharges(initialcharges);
        b.setMisccharges(misccharges);
        b.setPaydate(date);
        em.getTransaction().commit();
        System.out.println("Billing updated.");
    }

    
    
        public void updateGuest(int gid, String fname, String lname, String email, String phonenum) {
        em.getTransaction().begin();
        Guest g = em.find(Guest.class, gid);
        g.setfname(fname);
        g.setlname(lname);
        g.setemail(email);
        g.setphonenum(phonenum);
        em.getTransaction().commit();
        System.out.println("Guest updated.");
    }

    
    
}
