package db;

import java.util.Calendar;
import java.util.List;
import javax.persistence.*;
import model.*;

public class PersistenceOperations {

    EntityManagerFactory emf;
    EntityManager em;

    public PersistenceOperations() {
        emf = Persistence.createEntityManagerFactory("CA2Softdev3HotelPU");
        em = emf.createEntityManager();
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

      public void addReservation(Calendar cidate, Calendar codate, int numofAdults, int numofChildren, Calendar reservationDate) {
        em.getTransaction().begin();
        Reservation r = new Reservation(cidate,codate,numofAdults,numofChildren,reservationDate);
        em.persist(r);
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

    public void viewRoom() {
        em.getTransaction().begin();

        TypedQuery<Room> query
                = em.createQuery("SELECT ro FROM Room ro order by ro.roid",
                        Room.class);
        List<Room> results = query.getResultList();

        for (Room ro : results) {
            System.out.println(ro);
        }
        em.getTransaction().commit();

    }
    
    public void viewGuestReservations(int id){
    em.getTransaction().begin();
    Guest g = em.find(Guest.class, id);
    if(g==null){
        System.out.println("Guest does not exist, please try again!");
    } else {
        g.printReservations();
    }
    em.getTransaction().commit();
}
    

    public void close() {
        em.close();
        emf.close();
    }

}
