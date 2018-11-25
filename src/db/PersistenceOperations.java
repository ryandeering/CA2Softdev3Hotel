/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;
import java.util.Calendar;
import java.util.List;
import javax.persistence.*;
import model.*;

/**
 *
 * @author Ryan3
 * 
 */
public class PersistenceOperations {
    


    EntityManagerFactory emf;
    EntityManager em;

    public PersistenceOperations() {
        emf = Persistence.createEntityManagerFactory("CA2Softdev3HotelPU");
        em = emf.createEntityManager();
    }
    
     public void close() {
        em.close();
        emf.close();
    }
    
    
}
