package test;

import db.*;
import model.*;
import java.util.*;
import java.util.Calendar;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.GregorianCalendar;

public class TestReservationDB {

    public static void main(String[] args) throws IOException {
        BufferedReader stdin
                = new BufferedReader(new InputStreamReader(System.in));

        PersistenceOperations po = new PersistenceOperations();

        ReservationOperations ro = new ReservationOperations();

        Scanner sc = new Scanner(System.in);

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

        System.out.println("");
        System.out.println("");
        System.out.println("Welcome to the Heartbreak Hotel!");
        System.out.println("Established 1/11/2018!");
        System.out.println("Totally bug free! We think.");
        System.out.println("No on-side accidents but the JVM really likes eating RAM and CPU power.");

        boolean quit = false;
        while (!quit) {
            System.out.println("");
            System.out.println("");
            System.out.println("CREATE:");
            System.out.println("Please press 1 to create a reservation"); // handles billing, guest, reservation
            System.out.println("");
            System.out.println("");
            System.out.println("READ:");
            System.out.println("Please press 2 to see who is currently staying in the hotel."); // daria 
            System.out.println("Please press 3 to see who stayed in the hotel on a specified date."); //daria 
            System.out.println("Please press 4 to display reservation details for a guest"); //daria 
            System.out.println("Please press 5 to display rooms (all, taken, not taken)"); //daria 
            System.out.println("");
            System.out.println("");
            System.out.println("UPDATE:");
            System.out.println("Please press 6 to update reservation details"); // daria 
            System.out.println("Please press 7 to update guest details"); // ryan
            System.out.println("Please press 8 to update a guest's billing details."); // ryan
            System.out.println("");
            System.out.println("");
            System.out.println("DELETE:");
            System.out.println("Please press 9 to delete a reservation"); //ryan
            System.out.println("Please press 10 to delete a guest from the records."); // ryan
            // delete billing method might be unnecessary
            System.out.println("Press 11 to quit");

            String input = stdin.readLine();
            if (input == null) {
                break;
            }

            if (input.equals("")) {
                continue;
            }

            switch (input) {
                case "1":
                    // addReservation(Calendar checkinDate, Calendar checkoutDate, int numofAdults, int numofChildren, Calendar reservationDate) {
                    System.out.println("Enter Checkin Date (2018 01 30):");
                    input = stdin.readLine();
                    while (input == null) { //TODO: add more checks for error catching
                        System.out.println("Invalid Input try again!");
                        input = stdin.readLine();
                    }
                    String[] parts = input.split(" ");
                    Calendar checkinDate = new GregorianCalendar(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));

                    System.out.println("Enter Checkout Date (2018 01 30):");
                    input = stdin.readLine();
                    while (input == null) { //TODO: add more checks for error catching
                        System.out.println("Invalid Input try again!");
                        input = stdin.readLine();
                    }
                    parts = input.split(" ");
                    Calendar checkoutDate = new GregorianCalendar(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));

                    System.out.println("Enter how many adults:");
                    input = stdin.readLine();
                    while (input == null) { //TODO: add more checks for error catching
                        System.out.println("Invalid Input try again!");
                        input = stdin.readLine();
                    }
                    int adults = Integer.parseInt(input);

                    System.out.println("Enter how many children:");
                    input = stdin.readLine();
                    while (input == null) { //TODO: add more checks for error catching
                        System.out.println("Invalid Input try again!");
                        input = stdin.readLine();
                    }
                    int child = Integer.parseInt(input);

                    Calendar reservationDate = Calendar.getInstance();

                    po.addReservation(checkinDate, checkoutDate, adults, child, reservationDate);
                    break;
                case "2":
                    po.viewGuest();                    
                    break;
                case "3":
                    // addReservation(Calendar checkinDate, Calendar checkoutDate, int numofAdults, int numofChildren, Calendar reservationDate) {
                    System.out.println("Enter Checkin Date (2018 01 30):");
                    input = stdin.readLine();
                    while (input == null) { //TODO: add more checks for error catching
                        System.out.println("Invalid Input try again!");
                        input = stdin.readLine();
                    }
                    String[] partss = input.split(" ");
                    Calendar checkinDates = new GregorianCalendar(Integer.parseInt(partss[0]), Integer.parseInt(partss[1]), Integer.parseInt(partss[2]));

                    System.out.println("Enter Checkout Date (2018 01 30):");
                    input = stdin.readLine();
                    while (input == null) { //TODO: add more checks for error catching
                        System.out.println("Invalid Input try again!");
                        input = stdin.readLine();
                    }
                    partss = input.split(" ");
                    Calendar checkoutDates = new GregorianCalendar(Integer.parseInt(partss[0]), Integer.parseInt(partss[1]), Integer.parseInt(partss[2]));

                    System.out.println("Enter how many adults:");
                    input = stdin.readLine();
                    while (input == null) { //TODO: add more checks for error catching
                        System.out.println("Invalid Input try again!");
                        input = stdin.readLine();
                    }
                    int adultss = Integer.parseInt(input);

                    System.out.println("Enter how many children:");
                    input = stdin.readLine();
                    while (input == null) { //TODO: add more checks for error catching
                        System.out.println("Invalid Input try again!");
                        input = stdin.readLine();
                    }
                    int childs = Integer.parseInt(input);

                    Calendar reservationDates = Calendar.getInstance();

                    po.addReservation(checkinDates, checkoutDates, adultss, childs, reservationDates);
                    break;
                case "4":
                    System.out.println("Please input the guest id you want to view the reservations made for.");
                    int id = sc.nextInt();
                    sc.nextLine();
                    po.viewGuestReservations(id);
                    break;
                case "5":
                    po.viewRoom();
                    break;
                case "6":
                    System.out.println("Enter your reservation date:");
                    input = stdin.readLine();
                    ro.dropReservationTable();
                    break;
                case "7":
                    po.viewBilling();
                    break;
                case "11":
                    quit = true;
                    break;
                default:
                    System.out.println("Error! Invalid command");
                    break;
            }

        }
        ro.closeDB();
    }
}
