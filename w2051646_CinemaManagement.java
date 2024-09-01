/** "I confirm that I understand what plagiarism / collusion / contract cheating is and have read and understood the section on Assessment Offences in the Essential Information for Students.
 * The work that I have submitted is entirely my own. Any work from other authors is duly referenced and acknowledged."
 Name : R.M.M.Kavindu Uppala Rajapaksha
 Student ID : 20230351/w2051646 **/

import java.util.InputMismatchException;
import java.util.Scanner;

public class w2051646_CinemaManagement {

    /**
     * The CinemaManagement class of the Cinema Management application.
     * This class contains the main method which serves as the entry point for the application.
     * It initialises the seating arrangement and ticket array, and provides menu options to the user.
     */
    public static int[][] seats = new int[3][16];

    /**
     * Array storing ticket objects.
     * Each element represents a ticket purchased by a customer.
     */
    public static Ticket[]tickets = new Ticket[48];

    /**
     * Variables to store row number, seat number, and ticket index.
     */
    public static int row_num, seat_num, ticket_num = 0;

    /**
     * Variables to store  passenger name, surname, email, and ticket price.
     */
    public static String name, surname, email;

    public static double price;



    public static void main(String[] args) {


        System.out.println(" ");
        System.out.println("Welcome to The London Lumiere.");
        boolean isValid = true;
        while(isValid){
            System.out.println("-".repeat(48));
            System.out.println(" Please select an option: ");
            System.out.println("1) Buy a ticket ");
            System.out.println("2) Cancel  ticket ");
            System.out.println("3) See seating plan ");
            System.out.println("4) Find first seat available ");
            System.out.println("5) Print tickets information and total price");
            System.out.println("6) Search ticket ");
            System.out.println("7) Sort tickets by prize ");
            System.out.println("8) Exit ");
            System.out.println("-".repeat(48));

            try{
                Scanner scanner = new Scanner(System.in);
                System.out.println(" Select option: ");
                int option = scanner.nextInt();
                switch (option){
                    case 1:
                        buy_ticket();
                        break;
                    case 2:
                        cancel_ticket();
                        break;
                    case 3:
                        print_seating_area();
                        break;
                    case 4:
                        find_first_availabe();
                        break;
                    case 5:
                        print_ticket_info();
                        break;
                    case 6:
                        search_ticket();
                        break;
                    case 7:
                        sort_ticket();
                        break;
                    case 8:
                        isValid = false;
                        break;
                    default:
                        System.out.println("Please enter valid number....");
                        break;
                }


            } catch (InputMismatchException ex){
                System.out.println("Please enter a valid integer.");
            }

        }

    }

    /**
     * Prompts the user to select a seat by entering a row letter and seat number.
     * The method ensures the entered row and seat numbers are valid.
     * If the input is invalid, it prompts the user to enter the values again.
     */
    public static void selectSeat(){
        boolean isValid = true;
        while (isValid){
            try{
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter  valid row number ");
                row_num = scanner.nextInt();
                if (row_num > 0 && row_num < 4){
                    isValid = false;
                }else {
                    System.out.println("Please enter valid row number ");
                }
            }catch (InputMismatchException ex){
                System.out.println("Please enter valid integer....");
            }
        }

        while (!isValid) {
            try {
                Scanner scanner1 = new Scanner(System.in);
                System.out.println("Enter seat number: ");
                seat_num = scanner1.nextInt();
                if (seat_num > 0 && seat_num < 17) {
                    isValid = true;
                } else {
                    System.out.println("Please enter valid seat number.....");
                }
            } catch (InputMismatchException ex) {
                System.out.println("Please enter valid integer......");
            }
        }
    }

    /**
     * Allows the user to buy a ticket by selecting a seat and entering their details.
     * The method checks if the selected seat is already booked.
     * If the seat is available, it prompts the user to enter their first name, surname, and email.
     * It validates the entered details and calculates the ticket price.
     * A new ticket is created and added to the tickets array, and the seat is marked as sold.
     */
    public static void buy_ticket() {
        selectSeat();

        if (seats[row_num - 1 ][seat_num - 1 ] == 1) {
            System.out.println("This seat is already booked");
        } else {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter your first name: ");
            while (true) {
                name = scanner.nextLine().toUpperCase();
                if (!name.matches("[A-Z]*$")) {
                    System.out.println("Invalid name");
                    System.out.print("Enter your name again: ");
                } else break;
            }

            System.out.println("Enter your surname: ");
            while (true) {
                surname = scanner.nextLine().toUpperCase();
                if (!surname.matches("[A-Z]*$")) {
                    System.out.println("Invalid surname");
                    System.out.println("Enter your surname again: ");
                } else break;
            }

            while (true) {
                System.out.println("Enter your email: ");
                email = scanner.nextLine();
                if (email.matches("^[a-z0-9]+(?:\\.[a-z0-9]+)*@(?:[a-z]+\\.)+[a-z]{2,7}$")) {
                    break;
                } else
                    System.out.println("Invalid email. Please enter email again.");
            }

            Person person = new Person(name, surname, email);
            price = Ticket_price();

            Ticket ticket = new Ticket(row_num,seat_num,price,person);
            tickets[ticket_num] = ticket;
            ticket_num++;

            seats[row_num-1][seat_num-1] = 1;
            System.out.println("The seat has been booked");
        }

    }

    /**
     * Cancels a ticket by selecting a seat and marking it as available.
     * The method checks if the selected seat is already available.
     * If the seat is booked, it removes the associated ticket from the tickets array and marks the seat as available.
     */
    public static void cancel_ticket(){
        selectSeat();

        if (seats[row_num - 1][seat_num - 1] == 0) {
            System.out.println("This seat is already available.");
        } else {
            for (int i = 0; i < ticket_num; i++) {
                if (tickets[i] != null && tickets[i].getRow() == row_num && tickets[i].getSeat() == seat_num) {
                    tickets[i] = null;

                    seats[row_num - 1][seat_num - 1] = 0;
                    System.out.println("The seat has been cancelled successfully");
                }
            }
        }
    }

    /**
     * Prints the seating area of the theatre.
     * The seating area displays the seat numbers and their availability status.
     * 'O' indicates an available seat and 'X' indicates a sold seat.
     * The method also prints the ticket price for each row.
     */
    public static void print_seating_area() {
        System.out.println("*".repeat(17));
        System.out.println("*" + " ".repeat(5) + "SCREEN" + " ".repeat(5) + "*");
        System.out.println("*".repeat(17));
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                if (j == 8) {
                    System.out.print("  ");
                } else if (seats[i][j] == 0) {
                    System.out.print("O");
                } else{
                    System.out.print("X");
                }
            }
            System.out.println();
        }
    }

    /**
     * Finds and prints the first available seat in the seating area.
     * The method iterates through the seating arrangement and prints the row and seat number of the first available seat.
     */
    public static void find_first_availabe(){
        for (int i = 0; i<seats.length; i++){
            for (int j = 0; j < seats[i].length; j++) {
                if (seats[i][j] == 0) {
                    System.out.println("First available seat is in Row " + (i + 1) + " Seat " + (j + 1));
                    return;
                }
            }
        }
        System.out.println("No available seats.");
    }

    /**
     * Determines the ticket price based on the selected row number.
     * The method returns a price based on the row:
     * - Row 1: £12
     * - Row 2: £10
     * - Row 3: £8
     *
     * @return the price of the ticket based on the row number
     */
    public static double Ticket_price(){
        if (row_num == 1){
            return 12;
        } else if (row_num == 2) {
            return 10;
        }else {
            return 8;
        }
    }

    /**
     * Prints the information of all tickets sold during the session and calculates the total price.
     * If no tickets are sold, it prints a message indicating that no tickets were sold.
     * Otherwise, it prints the details of each sold ticket and the total price.
     */
    public static void print_ticket_info(){
        double totalTicket = 0;
        boolean isValid = true;
        System.out.println("Tickets sold during the session:");
        for (Ticket ticket : tickets) {
            if (ticket != null) {
                ticket.TicketInfo();
                totalTicket += ticket.getPrice();
                isValid = false;
            }
        }
        if (isValid) {
            System.out.println("No tickets sold during the session.");
        } else {
            System.out.println("Total price: £" + totalTicket);
        }
    }

    /**
     * Searches for a ticket based on the selected seat.
     * If the seat is found in the tickets array, it displays the ticket information.
     * If the seat is available (not found in the tickets array), it prints a message indicating that the seat is available.
     */
    public static void search_ticket(){
        selectSeat();

        boolean isValid = true;
        for (Ticket ticket : tickets) {
            if (ticket != null && ticket.getRow() == row_num && ticket.getSeat() == seat_num) {
                isValid = false;
                ticket.TicketInfo();
            }
        }
        if (isValid) {
            System.out.println("This seat is available.");
        }

    }
    /**
     * Sorts the tickets array by ticket price in ascending order using bubble sort algorithm.
     * Then, it prints the sorted tickets by displaying their information.
     */
    public static void sort_ticket(){
        for (int i = 0; i < ticket_num - 1; i++) {
            for (int j = 0; j < ticket_num - i - 1; j++) {
                if (tickets[j] != null && tickets[j + 1] != null && tickets[j].getPrice() > tickets[j + 1].getPrice()) {
                    Ticket temp = tickets[j];
                    tickets[j] = tickets[j + 1];
                    tickets[j + 1] = temp;
                }
            }
        }

        System.out.println("Sorted tickets by price:");
        for (Ticket ticket : tickets) {
            if (ticket != null) {
                ticket.TicketInfo();
            }
        }

    }
}
