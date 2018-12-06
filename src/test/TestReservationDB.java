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

// Software Development 3
// CA2
// Hotel Management System
// X00144631 Ryan Deering
// X00145914 Daria Denisiuk
// Made entirely through github: https://github.com/ryandeering/CA2Softdev3Hotel
public class TestReservationDB {

    public static void main(String[] args) throws IOException {
        BufferedReader stdin
                = new BufferedReader(new InputStreamReader(System.in));

        PersistenceOperations po = new PersistenceOperations();

        ReservationOperations ro = new ReservationOperations();

        Scanner sc = new Scanner(System.in);

        ro.openDB();

        ro.dropSequences(); // nice and tidy!

        ro.dropTables();

        ro.createTables();

        ro.createSequences();

        ro.fillTables();

        System.out.println("");
        System.out.println("");
        System.out.println("Welcome to the Heartbreak Hotel!");
        System.out.println("Established 1/11/2018!");
        System.out.println("Totally bug free! We think.");
        System.out.println("No on-side accidents but the JVM really likes eating RAM and CPU power.");

        boolean quit = false;
        while (!quit) {
            System.out.println("");

            System.out.println("CREATE:");
            System.out.println("Please press 1 to create a reservation"); // handles billing, guest, reservation
            System.out.println("");

            System.out.println("READ:");
            System.out.println("Please press 2 to see who is currently staying in the hotel."); // daria 
            System.out.println("Please press 3 to see who stayed in the hotel on a specified date."); //daria 
            System.out.println("Please press 4 to display reservation details for a guest"); //daria 
            System.out.println("Please press 5 to display rooms (all, taken, not taken)"); //daria 
            System.out.println("");

            System.out.println("UPDATE:");
            System.out.println("Please press 6 to update reservation details"); // daria 
            System.out.println("Please press 7 to update guest details"); // ryan
            System.out.println("Please press 8 to update a guest's billing details."); // ryan
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
                case "8":
                    System.out.println("Please enter the id of the guest who's billing you want to update");
                    int gid;
                    while (true) {
                        try {
                            gid = Integer.parseInt(sc.nextLine());
                            break;
                        } catch (NumberFormatException nfe) { // you can learn a thing or two about exception handling, even if it is only by osmosis! - rd
                            System.out.print("Enter a valid numerical value: ");
                        }
                    }

                    po.viewGuestBillings(gid);
                    System.out.println("");

                    System.out.println("Please enter the id of the billing you want to update.");
                    int bid;
                    while (true) {
                        try {
                            bid = Integer.parseInt(sc.nextLine());
                            break;
                        } catch (NumberFormatException nfe) {
                            System.out.print("Enter a valid numerical value: ");
                        }
                    }

                    System.out.println("Enter the new initial charges. This will overwrite the value printed.");

                    double initialcharges;
                    while (true) {
                        try {
                            initialcharges = Double.parseDouble(sc.nextLine());
                            break;
                        } catch (NumberFormatException nfe) {
                            System.out.print("Enter a valid numerical value: ");
                        }
                    }

                    System.out.println("Enter the new misc charges. This will overwrite the value printed.");

                    double misccharges;
                    while (true) {
                        try {
                            misccharges = Double.parseDouble(sc.nextLine());
                            break;
                        } catch (NumberFormatException nfe) {
                            System.out.print("Enter a valid numerical value: ");
                        }
                    }

                    Date date = new Date(); 
                    final Calendar cal = Calendar.getInstance();
                    cal.setTime(date);

                    po.updateBilling(bid, input, initialcharges, misccharges, cal);
                    
                    
                    
                    break;
                case "9":

                    System.out.println("Please enter the id of the guest who's reservation you want to delete");

                    while (true) {
                        try {
                            gid = Integer.parseInt(sc.nextLine());
                            break;
                        } catch (NumberFormatException nfe) {
                            System.out.print("Enter a valid numerical value: ");
                        }
                    }

                    System.out.println("Please enter the id of the reservation you wish to delete");
                    int rid;
                    while (true) {
                        try {
                            rid = Integer.parseInt(sc.nextLine());
                            break;
                        } catch (NumberFormatException nfe) {
                            System.out.print("Enter a valid numerical value: ");
                        }
                    }

                    Reservation r = po.findReservation(rid);
                    if (r != null) {
                        po.deleteReservation(rid, gid);
                        System.out.println("Reservation deleted.");
                    }

                    break;

                case "10":
                    System.out.println("Please enter the id of the guest who's id you want to delete.");
                    while (true) {
                        try {
                            gid = Integer.parseInt(sc.nextLine());
                            break;
                        } catch (NumberFormatException nfe) {
                            System.out.print("Enter a valid numerical value: ");
                        }
                    }

                    Guest g = po.findGuest(gid);
                    if (g != null) {
                        po.deleteGuest(gid);
                        System.out.println("Guest deleted.");
                    }

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
