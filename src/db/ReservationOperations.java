/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import oracle.jdbc.pool.OracleDataSource;

/**
 *
 * @author Ryan3
 */
public class ReservationOperations {

    private Connection conn;
    private PreparedStatement pstmt;

    public Connection openDB() {
        try {
            OracleDataSource ods = new OracleDataSource();

            ods.setURL("jdbc:oracle:thin:@localhost:1521:XE");
            ods.setUser("hr");
            ods.setPassword("passhr");

            conn = ods.getConnection();
            System.out.println("connected.");
        } catch (SQLException e) {
            System.out.print("Unable to load driver " + e.getMessage());
        }
        return conn;
    }

    public void dropBillingSequence() {
        try {
            String s2 = "drop sequence bill_seq";
            pstmt = conn.prepareStatement(s2);
            pstmt.executeUpdate();
            System.out.println("Billing Sequence dropped");
        } catch (SQLException ex) {

        }
    }

    public void createBillingSequence() {
        // Creating a sequence    
        try {
            String createseq1 = "create sequence bill_seq increment by 1 start with 1";
            pstmt = conn.prepareStatement(createseq1);
            pstmt.executeUpdate();
            System.out.println("Billing Sequence created");
        } catch (SQLException ex) {
            System.out.print("Problem with Billing Sequence " + ex.getMessage());
        }

    }

    public void dropGuestSequence() {
        try {
            String s2 = "drop sequence guest_seq";
            pstmt = conn.prepareStatement(s2);
            pstmt.executeUpdate();
            System.out.println("Guest Sequence dropped");
        } catch (SQLException ex) {

        }
    }

    public void createGuestSequence() {
        // Creating a sequence    
        try {
            String createseq1 = "create sequence guest_seq increment by 1 start with 1";
            pstmt = conn.prepareStatement(createseq1);
            pstmt.executeUpdate();
            System.out.println("Guest Sequence created");
        } catch (SQLException ex) {
            System.out.print("Problem with Guest Sequence " + ex.getMessage());
        }

    }

    public void dropReservationSequence() {
        try {
            String s2 = "drop sequence res_seq";
            pstmt = conn.prepareStatement(s2);
            pstmt.executeUpdate();
            System.out.println("Reservation Sequence dropped");
        } catch (SQLException ex) {

        }
    }

    public void createReservationSequence() {
        // Creating a sequence    
        try {
            String createseq1 = "create sequence res_seq increment by 1 start with 1";
            pstmt = conn.prepareStatement(createseq1);
            pstmt.executeUpdate();
            System.out.println("Reservation Sequence created");
        } catch (SQLException ex) {
            System.out.print("Problem with Reservation Sequence " + ex.getMessage());
        }

    }

    public void dropRoomSequence() {
        try {
            String s2 = "drop sequence res_seq";
            pstmt = conn.prepareStatement(s2);
            pstmt.executeUpdate();
            System.out.println("Room Sequence dropped");
        } catch (SQLException ex) {

        }
    }

    public void createRoomSequence() {
        // Creating a sequence    
        try {
            String createseq1 = "create sequence room_seq increment by 1 start with 1";
            pstmt = conn.prepareStatement(createseq1);
            pstmt.executeUpdate();
            System.out.println("Room Sequence created");
        } catch (SQLException ex) {
            System.out.print("Problem with Reservation Sequence " + ex.getMessage());
        }

    }

    public void createReservationtable() {
        // Create a Table           
        try {
            String sql = "CREATE TABLE Reservation (rid NUMBER PRIMARY KEY "
                    + "NOT NULL,"
                    + "checkinddate DATE,"
                    + "checkoutdate DATE,"
                    + "numofadults NUMBER,"
                    + "numofchildren NUMBER,"
                    + "reservationdate DATE"
                    + "FOREIGN KEY (guestid) REFERENCES GUEST (gid),"
                    + "FOREIGN KEY (roomid) REFERENCES ROOM (roid))";

            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
            System.out.println("TABLE RESERVATION created");
        } catch (SQLException ex) {
            System.out.println("SQL Exception creating "
                    + "Reservation table" + ex.getMessage());
        }
    }

    public void createRoomtable() {
        // Create a Table           
        try {
            String sql = "CREATE TABLE Room (roid NUMBER PRIMARY KEY "
                    + "NOT NULL," // taking ROIDS brother - HH
                    + "roomtype varchar2(100),"
                    + "numofbeds NUMBER,"
                    + "rate NUMBER)";

            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
            System.out.println("TABLE ROOM created");
        } catch (SQLException ex) {
            System.out.println("SQL Exception creating "
                    + "Room table" + ex.getMessage());
        }
    }

    public void createBillingtable() {
        // Create a Table           
        try {
            String sql = "CREATE TABLE Billing (bid NUMBER PRIMARY KEY "
                    + "NOT NULL,"
                    + "initialcharges NUMBER,"
                    + "misccharges NUMBER,"
                    + "paydate DATE)";

            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
            System.out.println("TABLE Billing created");
        } catch (SQLException ex) {
            System.out.println("SQL Exception creating "
                    + "Billing table" + ex.getMessage());
        }
    }

    public void createGuesttable() {
        // Create a Table           
        try {
            String sql = "CREATE TABLE Reservation (gid NUMBER PRIMARY KEY "
                    + "NOT NULL,"
                    + "fname varchar2(100),"
                    + "lname varchar2(100),"
                    + "email varchar2(100),"
                    + "phonenum varchar2(100),"
                    + "FOREIGN KEY (billid) REFERENCES ROOM (bid))";

            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
            System.out.println("TABLE RESERVATION created");
        } catch (SQLException ex) {
            System.out.println("SQL Exception creating "
                    + "Reservation table" + ex.getMessage());
        }
    }

    public void dropReservationTable() {
        System.out.println("Checking for existence of RESERVATION table");
        try {
            String s1 = "DROP TABLE RESERVATION CASCADE CONSTRAINTS";
            pstmt = conn.prepareStatement(s1);
            pstmt.executeUpdate();
            System.out.println("Reservation table dropped");
        } catch (SQLException ex) {

        }
    }

    public void dropBillingTable() {
        System.out.println("Checking for existence of Billing table");
        try {
            String s1 = "DROP TABLE BILLING CASCADE CONSTRAINTS";
            pstmt = conn.prepareStatement(s1);
            pstmt.executeUpdate();
            System.out.println("Billing table dropped");
        } catch (SQLException ex) {

        }
    }

    public void dropGuestTable() {
        System.out.println("Checking for existence of GUEST table");
        try {
            String s1 = "DROP TABLE GUEST CASCADE CONSTRAINTS";
            pstmt = conn.prepareStatement(s1);
            pstmt.executeUpdate();
            System.out.println("Guest table dropped");
        } catch (SQLException ex) {

        }
    }

    public void dropRoomTable() {
        System.out.println("Checking for existence of ROOM table");
        try {
            String s1 = "DROP TABLE ROOM CASCADE CONSTRAINTS";
            pstmt = conn.prepareStatement(s1);
            pstmt.executeUpdate();
            System.out.println("Room table dropped");
        } catch (SQLException ex) {

        }
    }

    public void closeDB() {
        try {
            pstmt.close();
            conn.close();
            System.out.println("Connection closed");
        } catch (SQLException ex) {
            System.out.println("Could not close connection " + ex.getMessage());
        }
    }
}
