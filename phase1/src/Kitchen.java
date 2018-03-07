import java.util.HashMap;

public class Kitchen extends Listener {

    private static HashMap<String, Dish> dishList;


    public Kitchen()
    {
        dishList = new HashMap<String, Dish>();
    }


    public static void addDish(Dish dish)
    {
        String id = String.valueOf(dish.getDishId());

        dishList.put(id, dish);
    }


    private static void removeDish(Dish dish)
    {
        String id = String.valueOf(dish.getDishId());

        dishList.remove(id);
    }


    private void readyDish(int dishID)
    {
        Dish dish = dishList.get(String.valueOf(dishID));

        dish.getWaiter().printToScreen("Dish " + dish.getDishId() + " for table " +
                dish.getTableNumber() + " is ready for pick-up.");

        removeDish(dish);
    }


    private void cancelDish(int dishID)
    {
        Dish dish = dishList.get(String.valueOf(dishID));

        removeDish(dish);
    }


    @Override
    public void handleEvent(String[] inputArray) {

    }


    @Override
    public void printToScreen(String s) {
        super.printToScreen(s);
    }
}
