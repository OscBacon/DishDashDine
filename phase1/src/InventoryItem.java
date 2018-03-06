public class InventoryItem {
    private int quantity;
    private int threshold;

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
