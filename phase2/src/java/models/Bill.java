package java.models;

import java.Dish;

import java.util.ArrayList;
import java.util.HashMap;

public class Bill {
    /**
     * The current number of bills.
     */
    private static int numOfBills;
    /**
     * This bill's unique ID.
     */
    private int billID;
    /**
     * This java.models.Bill's table.
     */
    private int tableNumber;
    /**
     * This java.models.Bill's java.models.Waiter.
     */
    private Waiter waiter;
    /**
     * A HashMap of this java.models.Bill's Dishes.
     */
    private HashMap<Integer, Dish> dishList;

    public Bill(int tableNumber, Waiter waiter) {
        this.tableNumber = tableNumber;
        this.waiter = waiter;
        this.dishList = new HashMap<>();
        this.billID = numOfBills;
        numOfBills++;
    }


    /**
     * Adds java.Dish to ArrayList of dishList
     *
     * @param dish java.models.Waiter passes in a dish to the bill which is added to ArrayList dishList
     */
    public void addDish(Dish dish) {
        dishList.put(dish.getDishId(), dish);
    }

    /**
     * Removes dish from java.models.Bill.
     *
     * @param currentDishID specific java.Dish ID passed to remove the dish from this specific java.models.Bill.
     */
    public void removeDish(int currentDishID) {
        dishList.remove(currentDishID);
    }

    /**
     * Returns this java.models.Bill's dishList.
     * @return dishList
     */
    public HashMap<Integer, Dish> getDishList() {
        return dishList;
    }

    private double getTotalBillPrice() {
        double price = 0.0;
        for (Integer key : dishList.keySet()) {
            price += dishList.get(key).getPrice();
        }
        return price;
    }

    /**
     * Returns this bill's ID.
     * @return An int representing this java.models.Bill's ID.
     */
    public int getBillID() {
        return billID;
    }

    /**
     * Returns the formatted bill as a String containing each dish, each dish's Price, total Price, the java.models.Waiter,
     * and the table number.
     *
     * @return String
     */
    public String toString() {
        ArrayList<String> billStrings = new ArrayList<>();
        billStrings.add("Thank you for joining us today. Your java.models.Waiter today was " + this.waiter.getName());
        billStrings.add("TABLE NUMBER: " + this.tableNumber);
        for (Integer key : dishList.keySet()) {
            String formattedPrice = String.format("%.2f",dishList.get(key).getPrice());
            billStrings.add("  " + dishList.get(key) + ": $" + formattedPrice);
        }
        billStrings.add("TOTAL PRICE: $" + String.format("%.2f", getTotalBillPrice()));
        return System.lineSeparator() + String.join(System.lineSeparator(), billStrings);
    }
}
