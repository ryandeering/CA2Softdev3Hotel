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

    public void addBilling(double initial_charges, double misc_charges, Calendar pay_date) {
        em.getTransaction().begin();
        Billing b = new Billing();
        b.setInitial_charges(initial_charges);
        b.setMisc_charges(misc_charges);
        b.setPay_date(pay_date);
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

      public void addReservation(Calendar checkinDate, Calendar checkoutDate, int numofAdults, int numofChildren, Calendar reservationDate) {
        em.getTransaction().begin();
        Reservation r = new Reservation(checkinDate,checkoutDate,numofAdults,numofChildren,reservationDate);
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

    public void close() {
        em.close();
        emf.close();
    }

}
