package test;

import db.*;
import model.*;
import java.util.Calendar;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.GregorianCalendar;

public class TestReservationDB {

    public static void main(String[] args) throws IOException {
        BufferedReader stdin =
                new BufferedReader(new InputStreamReader(System.in));
        
        PersistenceOperations po = new PersistenceOperations();

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

        

        boolean quit = false;
        while (!quit) {
            System.out.println("Please press 1 to create a reservation");
            System.out.println("Please press 2 to cancel a reservation");
            System.out.println("Please press 3 to update reservation details");
            System.out.println("Please press 4 to display reservation details for a guest");
            System.out.println("Please press 5 to display rooms (all, taken, not taken)");
            System.out.println("Please press 6 to display room properties");
            System.out.println("Please press 7 to display billing details for a guest");
            System.out.println("Press 8 to quit");

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
            Calendar checkinDate = new GregorianCalendar(Integer.parseInt(parts[0]),Integer.parseInt(parts[1]),Integer.parseInt(parts[2]));
            
            
            
            System.out.println("Enter Checkout Date (2018 01 30):");
            input = stdin.readLine();
            while (input == null) { //TODO: add more checks for error catching
                System.out.println("Invalid Input try again!");
                input = stdin.readLine(); 
            }
            parts = input.split(" ");
            Calendar checkoutDate = new GregorianCalendar(Integer.parseInt(parts[0]),Integer.parseInt(parts[1]),Integer.parseInt(parts[2]));
            
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
            
            Calendar reservationDate= Calendar.getInstance();
            
            po.addReservation(checkinDate, checkoutDate, adults, child, reservationDate);
                    break;
                case "2":
                    System.out.println("Enter your reservation date:");
                    input = stdin.readLine();
                     ro.dropReservationTable();
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
            Calendar checkinDates = new GregorianCalendar(Integer.parseInt(partss[0]),Integer.parseInt(partss[1]),Integer.parseInt(partss[2]));
            
            
            
            System.out.println("Enter Checkout Date (2018 01 30):");
            input = stdin.readLine();
            while (input == null) { //TODO: add more checks for error catching
                System.out.println("Invalid Input try again!");
                input = stdin.readLine(); 
            }
            partss = input.split(" ");
            Calendar checkoutDates = new GregorianCalendar(Integer.parseInt(partss[0]),Integer.parseInt(partss[1]),Integer.parseInt(partss[2]));
            
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
            
            Calendar reservationDates= Calendar.getInstance();
            
            po.addReservation(checkinDates, checkoutDates, adultss, childs, reservationDates);
                    break;
                case "4":
                    po.viewReservation();
                    break;
                case "5":
                    po.viewRoom();
                    break;
                case "6":
                    po.viewGuest();
                    break;
                case "7":
                    po.viewBilling();
                    break;
                case "8":
                    quit = true;
                    break;
                default:
                    System.out.println("Error! Invalid command");
                    break;
            }
ro.closeDB();
        }
    }
}
