// src/Menu.java
import java.util.ArrayList;
import java.util.List;

public class Menu {
    private List<MenuItem> items;

    public Menu() {
        this.items = new ArrayList<>();
        // Pre-populate the menu with some items
        items.add(new MenuItem("Coffee", 2.50));
        items.add(new MenuItem("Tea", 2.00));
        items.add(new MenuItem("Sandwich", 5.50));
        items.add(new MenuItem("Salad", 6.00));
        items.add(new MenuItem("Muffin", 3.00));
    }

    public void displayMenu() {
        System.out.println("----------- MENU -----------");
        for (int i = 0; i < items.size(); i++) {
            MenuItem item = items.get(i);
            System.out.printf("%d. %-15s $%.2f%n", (i + 1), item.getName(), item.getPrice());
        }
        System.out.println("----------------------------");
    }

    public MenuItem getMenuItem(int index) {
        if (index >= 0 && index < items.size()) {
            return items.get(index);
        }
        return null;
    }
}