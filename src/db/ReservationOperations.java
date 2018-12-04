package db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import oracle.jdbc.pool.OracleDataSource;

public class ReservationOperations {

    private Connection conn;
    private PreparedStatement pstmt;

    public Connection openDB() {
        try {
            OracleDataSource ods = new OracleDataSource();

       //     ods.setURL("jdbc:oracle:thin:@localhost:1521:XE");
        //    ods.setUser("hr");
          //  ods.setPassword("passhr"); // standard

            //   ods.setURL("jdbc:oracle:thin:@localhost:1521:XE");
            // ods.setUser("SYSTEM");
            // ods.setPassword("oracle"); // ryan linux
               ods.setURL("jdbc:oracle:thin:@//10.10.2.7:1521/global1");
             ods.setUser("hr");
             ods.setPassword("passhr"); // ryan college
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

        } catch (SQLException ex) {
            System.out.print("Problem with dropping Billing Sequence " + ex.getMessage());
        }
    }

    public void createBillingSequence() {
        // Creating a sequence    
        try {
            String createseq1 = "create sequence bill_seq increment by 1 start with 1";
            pstmt = conn.prepareStatement(createseq1);
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.print("Problem with Billing Sequence " + ex.getMessage());
        }

    }

    public void dropGuestSequence() {
        try {
            String s2 = "drop sequence guest_seq";
            pstmt = conn.prepareStatement(s2);
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.print("Problem with dropping Guest Sequence " + ex.getMessage());
        }
    }

    public void createGuestSequence() {
        // Creating a sequence    
        try {
            String createseq1 = "create sequence guest_seq increment by 1 start with 1";
            pstmt = conn.prepareStatement(createseq1);
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.print("Problem with Guest Sequence " + ex.getMessage());
        }

    }

    public void dropReservationSequence() {
        try {
            String s2 = "drop sequence res_seq";
            pstmt = conn.prepareStatement(s2);
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.print("Problem with dropping Reservation Sequence " + ex.getMessage());
        }
    }

    public void createReservationSequence() {
        // Creating a sequence    
        try {
            String createseq1 = "create sequence res_seq increment by 1 start with 1";
            pstmt = conn.prepareStatement(createseq1);
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.print("Problem with Reservation Sequence " + ex.getMessage());
        }

    }

    public void dropRoomSequence() {
        try {
            String s2 = "drop sequence room_seq";
            pstmt = conn.prepareStatement(s2);
            pstmt.executeUpdate();

        } catch (SQLException ex) {

        }
    }

    public void createRoomSequence() {
        // Creating a sequence    
        try {
            String createseq1 = "create sequence room_seq increment by 1 start with 1";
            pstmt = conn.prepareStatement(createseq1);
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.print("Problem with Reservation Sequence " + ex.getMessage());
        }

    }

    public void createReservationtable() {
        // Create a Table           
        try {
            String sql = "CREATE TABLE Reservation (rid NUMBER PRIMARY KEY "
                    + "NOT NULL,"
                    + "cidate DATE,"
                    + "codate DATE,"
                    + "numofadults NUMBER,"
                    + "numofchildren NUMBER,"
                    + "reservationdate DATE,"
                    + "gid NUMBER,"
                    + "roid NUMBER,"
                    + "FOREIGN KEY (gid) REFERENCES GUEST (gid),"
                    + "FOREIGN KEY (roid) REFERENCES ROOM (roid))";

            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();

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
                    + "roomtier varchar2(100),"
                    + "numofbeds NUMBER,"
                    + "rate NUMBER)";

            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();

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
                    + "paydate DATE,"
                    + "gid NUMBER,"
                    + "FOREIGN KEY (gid) REFERENCES GUEST (gid))";

            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("SQL Exception creating "
                    + "Billing table" + ex.getMessage());
        }
    }

    public void createGuesttable() {
        // Create a Table           
        try {
            String sql = "CREATE TABLE Guest (gid NUMBER PRIMARY KEY "
                    + "NOT NULL,"
                    + "fname varchar2(100),"
                    + "lname varchar2(100),"
                    + "email varchar2(100),"
                    + "phonenum varchar2(100))";

            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("SQL Exception creating "
                    + "Guest table" + ex.getMessage());
        }
    }

    public void dropReservationTable() {

        try {
            String s1 = "DROP TABLE RESERVATION CASCADE CONSTRAINTS";
            pstmt = conn.prepareStatement(s1);
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("SQL Exception dropping "
                    + "Reservation table" + ex.getMessage());
        }
    }

    public void dropBillingTable() {

        try {
            String s1 = "DROP TABLE BILLING CASCADE CONSTRAINTS";
            pstmt = conn.prepareStatement(s1);
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("SQL Exception dropping "
                    + "Billing table" + ex.getMessage());

        }
    }

    public void dropGuestTable() {

        try {
            String s1 = "DROP TABLE GUEST CASCADE CONSTRAINTS";
            pstmt = conn.prepareStatement(s1);
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("SQL Exception dropping "
                    + "Guest table" + ex.getMessage());
        }
    }

    public void dropRoomTable() {

        try {
            String s1 = "DROP TABLE ROOM CASCADE CONSTRAINTS";
            pstmt = conn.prepareStatement(s1);
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("SQL Exception dropping "
                    + "Room table" + ex.getMessage());
        }
    }

    public void fillGuestTable() {
        try {
            String sql = "INSERT INTO GUEST VALUES(guest_seq.nextVal,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, "Hulk");
            pstmt.setString(2, "Hogan");
            pstmt.setString(3, "hulkamaniac@brother.com");
            pstmt.setString(4, "21815200805");

            pstmt.executeUpdate();

            pstmt.setString(1, "Don");
            pstmt.setString(2, "Quixote");
            pstmt.setString(3, "sadboy@iol.com");
            pstmt.setString(4, "69420133718");

            pstmt.executeUpdate();

            pstmt.setString(1, "Karl");
            pstmt.setString(2, "Marx");
            pstmt.setString(3, "not@bourgie@hotmail.com");
            pstmt.setString(4, "22482974398");

            pstmt.executeUpdate();

            pstmt.setString(1, "SpongeBob");
            pstmt.setString(2, "Squarepants");
            pstmt.setString(3, "bahaha@pineapple.com");
            pstmt.setString(4, "22582238578");

            pstmt.executeUpdate();

            pstmt.setString(1, "Leo");
            pstmt.setString(2, "Francis");
            pstmt.setString(3, "mrweightlifter@bigboy.com");
            pstmt.setString(4, "33389568965");

            pstmt.executeUpdate();

            pstmt.setString(1, "Haruki");
            pstmt.setString(2, "Murakami");
            pstmt.setString(3, "hm@asahi-net.jp");
            pstmt.setString(4, "78798498576");

            pstmt.executeUpdate();

            pstmt.setString(1, "David");
            pstmt.setString(2, "Byrne");
            pstmt.setString(3, "talkinghead@gmail.com");
            pstmt.setString(4, "96636404420");

            pstmt.executeUpdate();

            pstmt.setString(1, "Mark");
            pstmt.setString(2, "E. Smith");
            pstmt.setString(3, "felldown@hotmail.com");
            pstmt.setString(4, "76082241511");

            pstmt.executeUpdate();

            pstmt.setString(1, "Ralf");
            pstmt.setString(2, "Hutter");
            pstmt.setString(3, "artsandcrafts@yahoo.co.uk");
            pstmt.setString(4, "99436916164");

            pstmt.executeUpdate();

            pstmt.setString(1, "Zsa Zsa");
            pstmt.setString(2, "Gabor");
            pstmt.setString(3, "aristocrat20@muchomail.ie");
            pstmt.setString(4, "90595768581");

            pstmt.executeUpdate();

            pstmt.setString(1, "Maureen");
            pstmt.setString(2, "O'Hara");
            pstmt.setString(3, "miracleon34th@street.com");
            pstmt.setString(4, "1637163954");
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("SQL Exception filling "
                    + "GUEST table" + ex.getMessage());
        }
    }

    public void fillBillingTable() {
        try {
            String sql = "INSERT INTO BILLING VALUES(bill_seq.nextVal,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);

            pstmt.setDouble(1, 120.00);
            pstmt.setDouble(2, 0.00);
            pstmt.setDate(3, Date.valueOf("2018-11-03"));
            pstmt.setInt(4, 1);
            pstmt.executeUpdate();

            pstmt.setDouble(1, 100.00);
            pstmt.setDouble(2, 20.00);
            pstmt.setDate(3, Date.valueOf("2018-11-03"));
            pstmt.setInt(4, 2);
            pstmt.executeUpdate();

            pstmt.setDouble(1, 1176.00);
            pstmt.setDouble(2, 20.00);
            pstmt.setDate(3, Date.valueOf("2018-11-11"));
            pstmt.setInt(4, 3);
            pstmt.executeUpdate();

            pstmt.setDouble(1, 420.00);
            pstmt.setDouble(2, 0.00);
            pstmt.setDate(3, Date.valueOf("2018-11-13"));
            pstmt.setInt(4, 1);
            pstmt.executeUpdate();

            pstmt.setDouble(1, 400.00);
            pstmt.setDouble(2, 20.00);
            pstmt.setDate(3, Date.valueOf("2018-11-07"));
            pstmt.setInt(4, 4);
            pstmt.executeUpdate();

            pstmt.setDouble(1, 120.00);
            pstmt.setDouble(2, 0.00);
            pstmt.setDate(3, Date.valueOf("2018-11-08"));
            pstmt.setInt(4, 5);
            pstmt.executeUpdate();

            pstmt.setDouble(1, 1008.00);
            pstmt.setDouble(2, 0.00);
            pstmt.setDate(3, Date.valueOf("2018-11-15"));
            pstmt.setInt(4, 6);
            pstmt.executeUpdate();

            pstmt.setDouble(1, 100.00);
            pstmt.setDouble(2, 0.00);
            pstmt.setDate(3, Date.valueOf("2018-11-16"));
            pstmt.setInt(4, 7);
            pstmt.executeUpdate();

            pstmt.setDouble(1, 288.00);
            pstmt.setDouble(2, 0.00);
            pstmt.setDate(3, Date.valueOf("2018-11-21"));
            pstmt.setInt(4, 8);
            pstmt.executeUpdate();

            pstmt.setDouble(1, 180.00);
            pstmt.setDouble(2, 40.00);
            pstmt.setDate(3, Date.valueOf("2018-11-25"));
            pstmt.setInt(4, 1);
            pstmt.executeUpdate();

            pstmt.setDouble(1, 100.00);
            pstmt.setDouble(2, 10.00);
            pstmt.setDate(3, Date.valueOf("2018-11-23"));
            pstmt.setInt(4, 9);
            pstmt.executeUpdate();

            pstmt.setDouble(1, 120.00);
            pstmt.setDouble(2, 0.00);
            pstmt.setDate(3, Date.valueOf("2018-11-28"));
            pstmt.setInt(4, 10);
            pstmt.executeUpdate();

            pstmt.setDouble(1, 60.00);
            pstmt.setDouble(2, 0.00);
            pstmt.setDate(3, Date.valueOf("2018-11-29"));
            pstmt.setInt(4, 11);
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("SQL Exception filling "
                    + "BILLING table" + ex.getMessage());
        }
    }

    public void fillRoomTable() {
        try {
            String sql = "INSERT INTO ROOM VALUES(room_seq.nextVal,?,?,?)";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, "Single Bed");
            pstmt.setInt(2, 1);
            pstmt.setDouble(3, 60.00);
            pstmt.executeUpdate();

            pstmt.setString(1, "Single Bed Premium");
            pstmt.setInt(2, 1);
            pstmt.setDouble(3, 120.00);
            pstmt.executeUpdate();

            pstmt.setString(1, "Double Bed");
            pstmt.setInt(2, 1);
            pstmt.setDouble(3, 100.00);
            pstmt.executeUpdate();

            pstmt.setString(1, "Double Bed");
            pstmt.setInt(2, 1);
            pstmt.setDouble(3, 100.00);
            pstmt.executeUpdate();

            pstmt.setString(1, "Single Bed");
            pstmt.setInt(2, 1);
            pstmt.setDouble(3, 60.00);
            pstmt.executeUpdate();

            pstmt.setString(1, "Family Room");
            pstmt.setInt(2, 3);
            pstmt.setDouble(3, 140.00);
            pstmt.executeUpdate();

            pstmt.setString(1, "Family Room");
            pstmt.setInt(2, 3);
            pstmt.setDouble(3, 140.00);
            pstmt.executeUpdate();

            pstmt.setString(1, "Double Bed Premium");
            pstmt.setInt(2, 1);
            pstmt.setDouble(3, 200.00);
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("SQL Exception filling "
                    + "ROOM table" + ex.getMessage());
        }
    }

    public void fillReservationTable() {
        try {
            String sql = "INSERT INTO RESERVATION VALUES(res_seq.nextVal,?,?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);

            pstmt.setDate(1, Date.valueOf("2018-11-04"));
            pstmt.setDate(2, Date.valueOf("2018-11-07"));
            pstmt.setInt(3, 1);
            pstmt.setInt(4, 0);
            pstmt.setDate(5, Date.valueOf("2018-11-01"));
            pstmt.setInt(6, 1);
            pstmt.setInt(7, 1);
            pstmt.executeUpdate();

            pstmt.setDate(1, Date.valueOf("2018-11-02"));
            pstmt.setDate(2, Date.valueOf("2018-11-03"));
            pstmt.setInt(3, 2);
            pstmt.setInt(4, 0);
            pstmt.setDate(5, Date.valueOf("2018-11-02"));
            pstmt.setInt(6, 2);
            pstmt.setInt(7, 5);
            pstmt.executeUpdate();

            pstmt.setDate(1, Date.valueOf("2018-11-04"));
            pstmt.setDate(2, Date.valueOf("2018-11-11"));
            pstmt.setInt(3, 2);
            pstmt.setInt(4, 2);
            pstmt.setDate(5, Date.valueOf("2018-11-04"));
            pstmt.setInt(6, 3);
            pstmt.setInt(7, 6);
            pstmt.executeUpdate();

            pstmt.setDate(1, Date.valueOf("2018-11-05"));
            pstmt.setDate(2, Date.valueOf("2018-11-13"));
            pstmt.setInt(3, 1);
            pstmt.setInt(4, 0);
            pstmt.setDate(5, Date.valueOf("2018-11-05"));
            pstmt.setInt(6, 1);
            pstmt.setInt(7, 1);
            pstmt.executeUpdate();

            pstmt.setDate(1, Date.valueOf("2018-11-05"));
            pstmt.setDate(2, Date.valueOf("2018-11-07"));
            pstmt.setInt(3, 2);
            pstmt.setInt(4, 0);
            pstmt.setDate(5, Date.valueOf("2018-11-05"));
            pstmt.setInt(6, 4);
            pstmt.setInt(7, 8);
            pstmt.executeUpdate();

            pstmt.setDate(1, Date.valueOf("2018-11-07"));
            pstmt.setDate(2, Date.valueOf("2018-11-08"));
            pstmt.setInt(3, 1);
            pstmt.setInt(4, 0);
            pstmt.setDate(5, Date.valueOf("2018-11-07"));
            pstmt.setInt(6, 5);
            pstmt.setInt(7, 2);
            pstmt.executeUpdate();

            pstmt.setDate(1, Date.valueOf("2018-11-08"));
            pstmt.setDate(2, Date.valueOf("2018-11-15"));
            pstmt.setInt(3, 2);
            pstmt.setInt(4, 2);
            pstmt.setDate(5, Date.valueOf("2018-11-06"));
            pstmt.setInt(6, 6);
            pstmt.setInt(7, 7);
            pstmt.executeUpdate();

            pstmt.setDate(1, Date.valueOf("2018-11-15"));
            pstmt.setDate(2, Date.valueOf("2018-11-16"));
            pstmt.setInt(3, 1);
            pstmt.setInt(4, 0);
            pstmt.setDate(5, Date.valueOf("2018-11-02"));
            pstmt.setInt(6, 7);
            pstmt.setInt(7, 3);
            pstmt.executeUpdate();

            pstmt.setDate(1, Date.valueOf("2018-11-19"));
            pstmt.setDate(2, Date.valueOf("2018-11-21"));
            pstmt.setInt(3, 1);
            pstmt.setInt(4, 1);
            pstmt.setDate(5, Date.valueOf("2018-11-19"));
            pstmt.setInt(6, 8);
            pstmt.setInt(7, 7);
            pstmt.executeUpdate();

            pstmt.setDate(1, Date.valueOf("2018-11-22"));
            pstmt.setDate(2, Date.valueOf("2018-11-25"));
            pstmt.setInt(3, 1);
            pstmt.setInt(4, 0);
            pstmt.setDate(5, Date.valueOf("2018-11-22"));
            pstmt.setInt(6, 1);
            pstmt.setInt(7, 1);
            pstmt.executeUpdate();

            pstmt.setDate(1, Date.valueOf("2018-11-23"));
            pstmt.setDate(2, Date.valueOf("2018-11-24"));
            pstmt.setInt(3, 2);
            pstmt.setInt(4, 0);
            pstmt.setDate(5, Date.valueOf("2018-11-23"));
            pstmt.setInt(6, 9);
            pstmt.setInt(7, 4);
            pstmt.executeUpdate();

            pstmt.setDate(1, Date.valueOf("2018-11-26"));
            pstmt.setDate(2, Date.valueOf("2018-11-28"));
            pstmt.setInt(3, 1);
            pstmt.setInt(4, 0);
            pstmt.setDate(5, Date.valueOf("2018-11-26"));
            pstmt.setInt(6, 10);
            pstmt.setInt(7, 5);
            pstmt.executeUpdate();

            pstmt.setDate(1, Date.valueOf("2018-11-28"));
            pstmt.setDate(2, Date.valueOf("2018-11-29"));
            pstmt.setInt(3, 1);
            pstmt.setInt(4, 0);
            pstmt.setDate(5, Date.valueOf("2018-11-28"));
            pstmt.setInt(6, 11);
            pstmt.setInt(7, 1);
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("SQL Exception filling "
                    + "Reservation table" + ex.getMessage());
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
