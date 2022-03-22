package businessLayer;

import java.io.Serializable;
import java.util.Objects;

/**
 * The type Menu item.
 */
public  abstract  class MenuItem implements Serializable {

    private int price;
    private  String nameMenuItem;
    private double rating;
    private int calories;
    private int protein;
    private int fat;
    private int sodium;


    /**
     * Gets rating.
     *
     * @return the rating
     */
    public double getRating() {
        return rating;
    }

    /**
     * Sets rating.
     *
     * @param rating the rating
     */
    public void setRating(double rating) {
        this.rating = rating;
    }

    /**
     * Gets calories.
     *
     * @return the calories
     */
    public int getCalories() {
        return calories;
    }

    /**
     * Sets calories.
     *
     * @param calories the calories
     */
    public void setCalories(int calories) {
        this.calories = calories;
    }

    /**
     * Gets protein.
     *
     * @return the protein
     */
    public int getProtein() {
        return protein;
    }

    /**
     * Sets protein.
     *
     * @param protein the protein
     */
    public void setProtein(int protein) {
        this.protein = protein;
    }

    /**
     * Gets fat.
     *
     * @return the fat
     */
    public int getFat() {
        return fat;
    }

    /**
     * Sets fat.
     *
     * @param fat the fat
     */
    public void setFat(int fat) {
        this.fat = fat;
    }

    /**
     * Gets sodium.
     *
     * @return the sodium
     */
    public int getSodium() {
        return sodium;
    }

    /**
     * Sets sodium.
     *
     * @param sodium the sodium
     */
    public void setSodium(int sodium) {
        this.sodium = sodium;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public int getPrice() {
        return price;
    }

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Gets name menu item.
     *
     * @return the name menu item
     */
    public String getNameMenuItem() {
        return nameMenuItem;
    }

    /**
     * Sets name menu item.
     *
     * @param nameMenuItem the name menu item
     */
    public void setNameMenuItem(String nameMenuItem) {
        this.nameMenuItem = nameMenuItem;
    }

    /**
     * Compute price int.
     *
     * @return the int
     */
    public abstract int computePrice();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if(nameMenuItem==null) return true;
        MenuItem menuItem = (MenuItem) o;
        return price == menuItem.price && nameMenuItem.equals(menuItem.nameMenuItem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, nameMenuItem);

    }

    @Override
    public String toString() {
        return "Name "+ getNameMenuItem()+"\n"
                +"Rating "+getRating()+"\n"
                +"Calories "+getCalories()+"\n"
                +"Protein "+getProtein()+"\n"
                +"Fat "+getFat()+"\n"
                +"Sodium "+getSodium()+"\n";
    }

}
