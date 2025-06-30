// src/Order.java
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Order {
    private List<OrderItem> items;
    private static final double TAX_RATE = 0.08; // 8% sales tax

    public Order() {
        this.items = new ArrayList<>();
    }

    public void addItem(OrderItem item) {
        this.items.add(item);
    }

    public double getSubtotal() {
        double subtotal = 0.0;
        for (OrderItem item : items) {
            subtotal += item.getSubtotal();
        }
        return subtotal;
    }

    public double getTax() {
        return getSubtotal() * TAX_RATE;
    }

    public double getTotal() {
        return getSubtotal() + getTax();
    }

    public void generateReceipt() {
        System.out.println("\n======= RECEIPT =======");
        System.out.println("Garments Cafeteria Inc.");
        
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("Date: " + now.format(formatter));
        System.out.println("-----------------------");

        System.out.printf("%-15s %-5s %-10s%n", "Item", "Qty", "Subtotal");
        for (OrderItem item : items) {
            System.out.printf("%-15s %-5d $%-9.2f%n", 
                item.getMenuItem().getName(), 
                item.getQuantity(), 
                item.getSubtotal());
        }
        
        System.out.println("-----------------------");
        System.out.printf("%-21s $%.2f%n", "Subtotal:", getSubtotal());
        System.out.printf("%-21s $%.2f%n", "Tax (8%):", getTax());
        System.out.println("-----------------------");
        System.out.printf("%-21s $%.2f%n", "TOTAL:", getTotal());
        System.out.println("=======================");
        System.out.println("Thank you for your visit!");
    }
}