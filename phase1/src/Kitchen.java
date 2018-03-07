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

    }


    private void cancelDish(int dishID)
    {

    }


    @Override
    public void handleEvent(String[] inputArray) {

    }


    @Override
    public void printToScreen(String s) {
        super.printToScreen(s);
    }
}
