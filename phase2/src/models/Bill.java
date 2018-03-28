package models;

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
     * This Bill's table.
     */
    private int tableNumber;
    /**
     * This Bill's Waiter.
     */
    private Waiter waiter;
    /**
     * A HashMap of this Bill's Dishes.
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
     * Adds Dish to ArrayList of dishList
     *
     * @param dish Waiter passes in a dish to the bill which is added to ArrayList dishList
     */
    public void addDish(Dish dish) {
        dishList.put(dish.getDishId(), dish);
    }

    /**
     * Removes dish from Bill.
     *
     * @param currentDishID specific Dish ID passed to remove the dish from this specific Bill.
     */
    public void removeDish(int currentDishID) {
        dishList.remove(currentDishID);
    }

    /**
     * Returns this Bill's dishList.
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
     * @return An int representing this Bill's ID.
     */
    public int getBillID() {
        return billID;
    }

    /**
     * Returns the formatted bill as a String containing each dish, each dish's Price, total Price, the Waiter,
     * and the table number.
     *
     * @return String
     */
    public String toString() {
        ArrayList<String> billStrings = new ArrayList<>();
        billStrings.add("Thank you for joining us today. Your Waiter today was " + this.waiter.getName());
        billStrings.add("TABLE NUMBER: " + this.tableNumber);
        for (Integer key : dishList.keySet()) {
            String formattedPrice = String.format("%.2f",dishList.get(key).getPrice());
            billStrings.add("  " + dishList.get(key) + ": $" + formattedPrice);
        }
        billStrings.add("TOTAL PRICE: $" + String.format("%.2f", getTotalBillPrice()));
        return System.lineSeparator() + String.join(System.lineSeparator(), billStrings);
    }
}
