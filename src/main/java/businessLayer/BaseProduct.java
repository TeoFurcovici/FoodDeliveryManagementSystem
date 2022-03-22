package businessLayer;

import businessLayer.MenuItem;

import java.util.ArrayList;
import java.util.HashSet;


/**
 * The type Base product.
 */
public class BaseProduct extends MenuItem {

    @Override
    public int computePrice() {
        return  getPrice();
    }




}
