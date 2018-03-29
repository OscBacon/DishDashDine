package models;

public class InventoryItem {
    private int quantity;
    private int threshold;
    private String name;

    public InventoryItem(String name, int quantity) {
        this.quantity = quantity;
        threshold = 10;
        this.name = name;
    }

    public InventoryItem(String name, int quantity, int threshold) {
        this.quantity = quantity;
        this.threshold = threshold;
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getThreshold() {
        return threshold;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name + ": {" +
                "quantity=" + quantity +
                ", threshold=" + threshold +
                '}';
    }
}
