// src/POSSystem.java
import java.util.InputMismatchException;
import java.util.Scanner;

public class POSSystem {

    private Menu menu;
    private Scanner scanner;

    public POSSystem() {
        this.menu = new Menu();
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        while (true) {
            displayMainMenu();
            int choice = getUserChoice();

            if (choice == 1) {
                handleNewOrder();
            } else if (choice == 2) {
                System.out.println("Exiting the POS system. Goodbye!");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    private void displayMainMenu() {
        System.out.println("\n===== Garments Cafeteria POS =====");
        System.out.println("1. Create New Order");
        System.out.println("2. Exit");
        System.out.print("Please select an option: ");
    }



    private void handleNewOrder() {
        Order currentOrder = new Order();
        System.out.println("\n--- New Order Started ---");

        while (true) {
            menu.displayMenu();
            System.out.println("Enter the item number to add to the order (or 0 to finish):");
            int itemChoice = getUserChoice();

            if (itemChoice == 0) {
                break; // Finish the order
            }

            MenuItem selectedItem = menu.getMenuItem(itemChoice - 1);
            if (selectedItem == null) {
                System.out.println("Invalid item number. Please try again.");
                continue;
            }

            System.out.printf("Enter quantity for %s: ", selectedItem.getName());
            int quantity = getUserChoice();
            if (quantity > 0) {
                currentOrder.addItem(new OrderItem(selectedItem, quantity));
                System.out.printf("%d x %s added to the order.%n%n", quantity, selectedItem.getName());
            } else {
                System.out.println("Invalid quantity. Please enter a positive number.");
            }
        }

        // Process payment and generate receipt
        if (currentOrder.getSubtotal() > 0) {
            processPayment(currentOrder);
        } else {
            System.out.println("Order cancelled as no items were added.");
        }
    }
    
    private void processPayment(Order order) {
        order.generateReceipt();
        double total = order.getTotal();
        
        while (true) {
            System.out.printf("Total amount due: $%.2f%n", total);
            System.out.print("Enter cash received from customer: ");
            double cashReceived = getDoubleInput();
            
            if (cashReceived >= total) {
                double change = cashReceived - total;
                System.out.printf("Change to be given: $%.2f%n", change);
                System.out.println("--- Order Complete ---");
                break;
            } else {
                System.out.println("Insufficient cash received. Please enter an amount greater than or equal to the total.");
            }
        }
    }

    private int getUserChoice() {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.next(); // Clear the invalid input
            return -1; // Return an invalid choice
        }
    }
    
    private double getDoubleInput() {
        try {
            return scanner.nextDouble();
        } catch (InputMismatchException e) {
            scanner.next(); // Clear the invalid input
            return -1.0; // Return an invalid value
        }
    }

    public static void main(String[] args) {
        POSSystem pos = new POSSystem();
        pos.run();
    }
}