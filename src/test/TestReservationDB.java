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
        ro.dropBillingSequence();
        ro.dropGuestSequence();
        ro.dropRoomSequence();
        ro.dropReservationSequence();

        ro.dropBillingTable();
        ro.dropGuestTable();
        ro.dropRoomTable();
        ro.dropReservationTable();

        ro.createBillingtable();
        ro.createGuesttable();
        ro.createRoomtable();
        ro.createReservationtable();

        ro.createBillingSequence();
        ro.createGuestSequence();
        ro.createReservationSequence();
        ro.createRoomSequence();

        ro.fillBillingTable();
        ro.fillGuestTable();
        ro.fillRoomTable();
        ro.fillReservationTable();

        ro.closeDB();

    }

}
