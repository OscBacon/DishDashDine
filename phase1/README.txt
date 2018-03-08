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


    Server inputs:

        - When a waiter requests a bill, the format is as follows:
            String waiter, String billNumber

            waiter + " | requested bill | " + billNumber


        - When a waiter cancels a dish, the format is as follows:
            String dishID

            "Kitchen | Dish | " + dishID + " | cancelled."


        - When a waiter places a dish order, the format is as follows:
            String waiter, String itemName

            waiter + " | ordered | " + itemName
    }

    /**
     * Writes to events.txt, simulating the input of a waiter placing an order of a menu item with substitutions.
     *
     * @param waiter       The waiter who places the order.
     * @param itemName     The name of the menu item that is being ordered.
     * @param additions    The ingredients that must be added to the ordered item.
     * @param subtractions The ingredients that must be removed from the ordered item.
     */
    // Additions and/or subtractions can be empty strings.
    public void orderDish(String waiter, String itemName, String additions, String subtractions) {
        eventWriter(waiter + " | ordered | " + itemName + " | " + additions + " | " + subtractions);
    }

    /**
     * Writes to events.txt, simulating the input of waiter confirming that a dish has been delivered to its table.
     *
     * @param waiter THe waiter who's serving the table and the dish.
     * @param dishID The id of the dish that has been served.
     */
    public void confirmDelivery(String waiter, String dishID) {
        eventWriter(waiter + " | delivered dish | " + dishID);
    }

    /**
     * Writes to events.txt, simulating the input of a waiter letting the kitchen know that a client recalled a dish.
     *
     * @param waiter The waiter whose clients recalled the dish.
     * @param dishID The id of the dish that was recalled.
     */
    public void dishRecall(String waiter, String dishID) {
        eventWriter(waiter + " | recalled dish | " + dishID);
    }


    // Receiver inputs:

    /**
     * Writes to events.txt, simulating the input of a receiver inputting the arrival of some ingredient.
     *
     * @param ingredient The ingredient that has arrived.
     * @param quantity   The quantity of this ingredient that has arrived.
     */
    public void addToInventory(String ingredient, String quantity) {
        eventWriter("Restaurant | addToInventory | " + ingredient + " | " + quantity);
    }
