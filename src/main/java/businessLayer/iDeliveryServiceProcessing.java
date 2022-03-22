package businessLayer;


import java.io.IOException;
import java.util.*;

public interface iDeliveryServiceProcessing {
    /**
     * @pre the menuItem must not  be null
     * @pre the businessLayer.DeliveryService must not be empty
     * @post the menuItem was created
     * @post the businessLayer.DeliveryService must not be empty
     * @param menuItem
     */

    HashSet<MenuItem> createMenuItem(MenuItem menuItem);

    /**
     * @pre the menuItem must not  be null
     * @pre the menuItem must exist
     * @pre the delivery service is well formed
     * @post the menuItem was deleted
     * @post the  delivery service is well formed
     * @param menuItem
     */
    HashSet<MenuItem> deleteMenuItem(MenuItem menuItem);

    /**
     * @pre the menuItem must not be null
     * @pre the menuItem must exist
     * @pre the delivery service is well formed
     * @post the menuItem has been modified
     * @post the delivery service is well formed
     * @param menuItem
     */
    HashSet<MenuItem>  modifyMenuItem(MenuItem menuItem) throws IOException;

    /**
     * @pre oreder !=null
     * @pre the delivery service is well formed
     * @post the delivery service is well formed
     * @param order
     * @param menuItemHashSet
     */
    void createOrderCleint(Order order, HashSet<MenuItem> menuItemHashSet);

    /**
     * @pre order must not be null
     * @pre the delivery service is well formed
     * @post the bill is generated
     * @post the delivery service is well formed
     * @param string
     */
    void generateBill(String string);


    /**
     * @pre order must not be null
     * @pre the delivery service is well formed
     * @post the delivery service is well formed
     * @post the price must be a positive value(>0)
     * @param order
     * @return
     */
    int computePriceForClientOrder(Order order);

    /**
     * @pre the delivery service must be well formed
     * @post the products were added
     * @post the delivery service is well formed
     */
    HashSet<MenuItem>  importProducts();

    /**
     * @pre the arguments must not be null
     * @pre the delivery service must be well formed
     * @param name
     * @param itemIngredients
     * @post the delivery service must be well formed
     * @post the composite product is created
     * @return
     */
      CompositeProduct createComposite(String name, ArrayList<MenuItem> itemIngredients);
       Collection<Order> report1(HashMap<Order, HashSet<MenuItem>> orders, int start, int end);
     Collection<MenuItem> report2(HashMap<Order, HashSet<MenuItem>> orders, int threshold);
     Collection<MenuItem> report2Final(int threshold);
    Collection<Integer> report3(HashMap<Order, HashSet<MenuItem>> orders, int thresholdOrders, double thresholdAmount);
    HashMap<MenuItem, Integer> report4(HashMap<Order, HashSet<MenuItem>> orders, int day);
}
