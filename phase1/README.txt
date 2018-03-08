README of DishDashDine, a student made application:
----------------------------------------------------

DishDashDine is a program that helps restaurants adapt to a dynamic industry. Many restaurants are giving their waiters
tablets into which they can enter orders. DishDashDine aims to avoid the use of paper by automating a majority of the
actions by waiters and cooks. This program aims to streamline interactions between the stakeholders in a restaurant.

Enjoy!

- Sincerely, the Dev Team


=======================================================================================================================


Libraries:

    We've included a gson-2.8.2.jar file in the lib directory. Please include this library when using our program;
    it allows us to parse and write to / read from .json files, which is necessary for our program. Thanks!


Notes:

    A dish may be cancelled only before some cook confirms it. Once confirmed by a cook, the server can at most remove
    the dish, but the ingredients that were to be used for the dish are not recovered - they're considered gone.

    We have included several dishes in our Menu but to try a more extensive list, feel free to follow the menu.json
    format and add dishes that you would like to order. Duly note however that you might have to update the inventory to
    include a wider range of ingredients!

Input format to events.txt:

    *** Please note: We have a class called Actions that writes to events.txt the input you wish to simulate with a
        simple function call. Unfortunately, as of yet, since we need to save the events.txt file after each input, the
        Actions.java class is unfortunately not running with high reliability. We recommend you follow the template
        below to manually write to the events.txt file.


    Kitchen inputs:
    ---------------

        - When a cook confirms that he has seen a dish, the format is as follows:
            String cookName, String dishID

            "Kitchen | " + cookName + " | has accepted dish | " + dishID


        - When a cook confirms that a dish is ready for the server to pick up, the format is as follows:
            String dishID

            "Kitchen | Dish | " + dishID + " | is ready."


    Waiter inputs:
    --------------

        - When a waiter requests a bill, the format is as follows:
            String waiter, String tableNumber

            waiter + " | requested bill for table | " + tableNumber


        - When a waiter requests to see any bill (active or non-active), the format is as follows:
            String waiter, String billNumber

            waiter + " | requested bill | " + billNumber


        - When a waiter cancels a dish, the format is as follows:
            String waiter, String dishID

            waiter + " | cancelled dish | " + dishID


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


        - When a waiter starts a new bill, the format is as follows:
             String waiter, String tableNumber

             waiter + " | new bill | " + tableNumber


        - When a waiter's bill gets paid, the format is as follows:
            String waiter, String tableNumber

            waiter + " | pay bill | " + tableNumber


        - When a waiter must remove a dish from the table, the format is as follows:
            String waiter, String dishID

            waiter + " | removed dish | " + dishID


    Receiver inputs:
    ----------------

         - When a receiver wishes to add an ingredient to inventory, the format is as follows:
            String ingredient, String quantity

            "Restaurant | addToInventory | " + ingredient + " | " + quantity


    Manager inputs:
    ---------------

        - When a manager wishes to add a waiter into the system, the format is as follows:
            String waiterName

            "Restaurant | addWaiter | " + waiterName


        - When a manager wishes to remove a waiter from the system, the format is as follows:
            String waiterName

            "Restaurant | removeWaiter | " + waiterName


=======================================================================================================================


    Example Scenario (to be entered line by line in events.txt):
    Please note that after entering each line in events.txt, do not press enter as that will result in a NullPointer
    Exception.

        Waiter Dennis | new bill | 2
        Waiter Dennis | ordered | Kung Pao Chicken | for table | 2
        Waiter Dennis | ordered | Kung Pao Chicken | salad | | for table | 2
        Kitchen | Mike | has accepted dish | 0
        Kitchen | Fleur | has accepted dish | 1
        Kitchen | Dish | 0 | is ready.
        Kitchen | Dish | 1 | is ready.
        Waiter Dennis | delivered dish | 0
        Waiter Dennis | recalled dish | 1
        Waiter Dennis | requested bill for table | 2
        Kitchen | Mike | has accepted dish | 1
        Kitchen | Dish | 1 | is ready.
        Waiter Dennis | delivered dish | 1
        Waiter Dennis | pay bill | 2
