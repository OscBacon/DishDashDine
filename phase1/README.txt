How to use DishDashDine, a student made application:


DishDashDine is a program that helps restaurants adapt to a dynamic industry. Many restaurants are giving their waiters
tablets into which they can enter orders. DishDashDine aims to avoid the use of paper by automating a majority of the
actions by waiters and cooks. This program aims to streamline interactions between the stakeholders in a restaurant.


Input format to events.txt:


    Kitchen inputs:

        - When a cook confirms that he has seen a dish, the format is as follows:
            String cookName, String dishID

            "Kitchen | " + cookName + " | has accepted dish | " + dishID


        - When a cook confirms that a dish is ready for the server to pick up, the format is as follows:
            String dishID

            "Kitchen | Dish | " + dishID + " | is ready."


    Waiter inputs:

        - When a waiter requests a bill, the format is as follows:
            String waiter, String billNumber

            waiter + " | requested bill | " + billNumber


        - When a waiter cancels a dish, the format is as follows:
            String dishID

            "Kitchen | Dish | " + dishID + " | cancelled."


        - When a waiter places a dish order without substitutions, the format is as follows:
            String waiter, String itemName, String tableNumber

            waiter + " | ordered | " + itemName + " | for table | " + tableNumber


        - When a waiter places a dish order with substitutions, the format is as follows:
            // Additions and/or subtractions can be empty strings.
            String waiter, String itemName, String additions, String subtractions, String tableNumber

            waiter + " | ordered | " + itemName + " | " + additions + " | " + subtractions + " | for table | " + tableNumber


        - When a waiter confirms that a dish has been delivered, the format is as follows:
            String waiter, String dishID

            waiter + " | delivered dish | " + dishID


        - When a waiter recalls a dish, the format is as follows:
            String waiter, String dishID

            waiter + " | recalled dish | " + dishID


    Receiver inputs:

         - When a receiver wishes to add an ingredient to inventory, the format is as follows:
            String ingredient, String quantity

            "Restaurant | addToInventory | " + ingredient + " | " + quantity


Example scenario, to be entered line by line in events.txt:

Waiter Dennis | new bill | 2
Waiter Dennis | ordered | Kung Pao Chicken | for table | 2
Kitchen | Mike | has accepted dish | 0
Kitchen | Dish | 0 | is ready.
Waiter Dennis | delivered dish | 0
Waiter Dennis | requested bill | 2
Waiter Dennis | ordered | Kung Pao Chicken | salad | | for table | 2
Waiter Dennis | pay bill  | 2
