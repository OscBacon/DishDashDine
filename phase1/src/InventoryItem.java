public class InventoryItem {
    private int quantity;
    private int threshold;

    public InventoryItem(int quantity) {
        this.quantity = quantity;
        threshold = 10;
    }

    public InventoryItem(int quantity, int threshold) {
        this.quantity = quantity;
        this.threshold = threshold;
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

    @Override
    public String toString() {
        return "{" +
                "quantity=" + quantity +
                ", threshold=" + threshold +
                '}';
    }
}
