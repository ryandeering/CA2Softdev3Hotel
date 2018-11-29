package test;

import db.*;
import model.*;
import java.util.Calendar;
import java.util.Scanner;

public class TestReservationDB {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        ReservationOperations ro = new ReservationOperations();
        ro.openDB();
        
        ro.dropGuestSequence();
        ro.dropBillingSequence();
        ro.dropRoomSequence();
        ro.dropReservationSequence();

        ro.dropGuestTable();
        ro.dropBillingTable();
        ro.dropRoomTable();
        ro.dropReservationTable();

        ro.createGuesttable();
        ro.createBillingtable();
        ro.createRoomtable();
        ro.createReservationtable();

        ro.createGuestSequence();
        ro.createBillingSequence();
        ro.createReservationSequence();
        ro.createRoomSequence();

        ro.fillGuestTable();
        ro.fillBillingTable();
        ro.fillRoomTable();
        ro.fillReservationTable();

        ro.closeDB();

    }

}
