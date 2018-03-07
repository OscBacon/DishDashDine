public class InventoryItem {
    private int quantity;
    private int threshold;

    public InventoryItem(int quantity, int threshold) {
        this.quantity = quantity;
        this.threshold = threshold;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getThreshold() {
        return threshold;
    }

    @Override
    public String toString() {
        return "{" +
                "quantity=" + quantity +
                ", threshold=" + threshold +
                '}';
    }
}
