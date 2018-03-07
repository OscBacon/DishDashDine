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


    private void readyDish(String dishID)
    {
        Dish dish = dishList.get(dishID);

        dish.getWaiter().printToScreen("Dish " + dish.getDishId() + " for table " +
                dish.getTableNumber() + " is ready for pick-up.");

        removeDish(dish);
    }


    private void cancelDish(String dishID)
    {
        Dish dish = dishList.get(dishID);

        removeDish(dish);
    }


    @Override
    public void handleEvent(String[] inputArray) {
        if (inputArray.length >= 3) {

            if (inputArray[2].trim().equals("is ready."))
            {
                this.readyDish(inputArray[1]);
            }

            else if (inputArray[2].trim().equals("cancelled."))
            {
                this.cancelDish(inputArray[1]);
            }
        }
    }


    @Override
    public void printToScreen(String s) {
        super.printToScreen(s);
    }
}
