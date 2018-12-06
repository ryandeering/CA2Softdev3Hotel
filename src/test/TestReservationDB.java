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
            System.out.println("Please press 1 to create a reservation"); // daria doneish, needs input for guest data
            System.out.println("Please press 2 to check-in"); // this will create entry for billing, ryan                                         
            System.out.println("");

            System.out.println("READ:");
            System.out.println("Please press 3 to see who is currently staying in the hotel."); // daria  todo
            System.out.println("Please press 4 to see who stayed in the hotel on a specified date."); //daria todo
            System.out.println("Please press 5 to display reservation details for a guest"); //daria done
            System.out.println("Please press 6 to display all rooms"); //done
            System.out.println("");

            System.out.println("UPDATE:");
            System.out.println("Please press 7 to update reservation details"); // daria done
            System.out.println("Please press 8 to update guest details"); // ryan done
            System.out.println("Please press 9 to update a guest's billing details."); // ryan done
            System.out.println("");

            System.out.println("DELETE:");
            System.out.println("Please press 10 to delete a reservation"); //ryan done
            System.out.println("Please press 11 to delete a guest from the records."); // ryan not done
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

                    System.out.println("Enter Checkin Date (DD-MM-YY):");
                    input = sc.nextLine();
                    while (input == null) {
                        System.out.println("Invalid Input try again!");
                        input = sc.nextLine();
                    }
                    String[] parts = input.split("-");
                    Calendar checkinDate = new GregorianCalendar(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));

                    System.out.println("Enter Checkout Date (DD-MM-YY):");
                    input = sc.nextLine();
                    while (input == null) {
                        System.out.println("Invalid Input try again!");
                        input = sc.nextLine();
                    }
                    parts = input.split("-");
                    Calendar checkoutDate = new GregorianCalendar(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));

                    System.out.println("Enter how many adults:");
                    input = sc.nextLine();
                    while (input == null) {
                        System.out.println("Invalid Input try again!");
                        input = sc.nextLine();
                    }
                    int adults = Integer.parseInt(input);

                    System.out.println("Enter how many children:");
                    input = sc.nextLine();
                    while (input == null) {
                        System.out.println("Invalid Input try again!");
                        input = sc.nextLine();
                    }

                    int child = Integer.parseInt(input);

                    Calendar reservationDate = Calendar.getInstance();

                    po.addReservation(checkinDate, checkoutDate, adults, child, reservationDate);
                    System.out.println("Reservation added!");
                    break;

                case "2":
                  po.viewGuest();// placeholder NOT FINISHED
                          break;

                case "3":
                    po.viewGuest(); //placeholder NOT FINISHED
                    break;
                case "4":
                    System.out.println("Enter Checkin Date (DD-MM-YY):");
                    input = sc.nextLine();
                    while (input == null) {
                        System.out.println("Invalid Input try again!");
                        input = sc.nextLine();
                    } // NOT FINISHED

                    po.viewGuestReservationsDate(input);
                    break;
                case "5":
                    System.out.println("Please input the guest id you want to view the reservations made for.");
                    int id = sc.nextInt();

                    while (true) {
                        try {
                            id = Integer.parseInt(sc.nextLine());
                            break;
                        } catch (NumberFormatException nfe) {
                            System.out.print("Enter a valid numerical value: ");
                        }
                    } // DONE

                    po.viewGuestReservations(id);
                    break;
                case "6":
                    System.out.println("<======= All =======>");
                    po.viewRoom();

                    break; // DONE
                case "7": // DONE
                    while (input == null) {
                        System.out.println("Invalid Input try again!");
                        input = sc.nextLine();
                    }
                    ;
                    String checkinDate3 = input;
                    System.out.println("Enter Updated Checkout Date (DD-MMM-YY):");
                    input = sc.nextLine();
                    while (input == null) {
                        System.out.println("Invalid Input try again!");
                        input = sc.nextLine();
                    }

                    String checkoutDate3 = input;
                    System.out.println("Enter how many adults:");
                    input = sc.nextLine();
                    while (input == null) {
                        System.out.println("Invalid Input try again!");
                        input = sc.nextLine();
                    }
                    int adults3 = Integer.parseInt(input);
                    System.out.println("Enter how many children:");
                    input = sc.nextLine();
                    while (input == null) {
                        System.out.println("Invalid Input try again!");
                        input = sc.nextLine();
                    }
                    int child3 = Integer.parseInt(input);

                    System.out.println("Enter Updated Reservation Date (DD-MMM-YY):");
                    input = sc.nextLine();
                    while (input == null) {
                        System.out.println("Invalid Input try again!");
                        input = sc.nextLine();
                    }

                    String reservationDate3 = input;

                    System.out.println("Enter your reservation id:");
                    int rid3 = sc.nextInt();
                    po.updateReservation(rid3, checkinDate3, checkoutDate3, adults3, child3, reservationDate3);

                    break;

                case "8": // DONE 
                    System.out.println("Please enter the id of the guest who's details you want to update.");
                    int gid;
                    while (true) {
                        try {
                            gid = Integer.parseInt(sc.nextLine());
                            break;
                        } catch (NumberFormatException nfe) { // you can learn a thing or two about exception handling, even if it is only by osmosis! - rd
                            System.out.print("Enter a valid numerical value: ");
                        }
                    }

                    Guest gg = po.findGuest(gid);

                    if (gg != null) {

                        System.out.println(po.findGuest(gid));

                        System.out.println("Input new first name.");
                        String fname = sc.nextLine();

                        System.out.println("Input new last name.");
                        String lname = sc.nextLine();

                        System.out.println("Input new email.");
                        String email = sc.nextLine();

                        System.out.println("Input new phone number.");
                        String phonenum = sc.nextLine();

                        po.updateGuest(gid, fname, lname, email, phonenum);
                    } else {
                        System.out.println("Please try again.");
                    }

                    break;
                case "9": // DONE
                    System.out.println("Please enter the id of the guest who's billing you want to update");

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
                case "10": // DONE

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

                case "11": // NOT DONE
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
                case "12":
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
