package businessLayer;


import java.util.ArrayList;
import java.util.List;

/**
 * The type Composite product.
 */
public class CompositeProduct extends MenuItem  {

    private ArrayList<MenuItem> compositeProducts=new ArrayList<>();

    /**
     * Gets composite products.
     *
     * @return the composite products
     */
    public List<businessLayer.MenuItem> getCompositeProducts() {
        return compositeProducts;
    }

    /**
     * Sets composite products.
     *
     * @param menuItemList the menu item list
     */
    public void setCompositeProducts(ArrayList<businessLayer.MenuItem> menuItemList) {
        this.compositeProducts = menuItemList;
    }


    @Override
    public int computePrice() {
        int price=0;
        for (MenuItem currMenuItem:compositeProducts) {
            price=price+currMenuItem.getPrice();
        }
        return  price;
    }




}
