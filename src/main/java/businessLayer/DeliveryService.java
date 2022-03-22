package businessLayer;

import dataLayer.FileWriter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * The type Delivery service.
 */
public class DeliveryService extends Observable implements iDeliveryServiceProcessing,Serializable {

    private HashMap<Order, HashSet<MenuItem>> orders;
    private  HashSet<MenuItem> menuItems;
    /**
     * The J table.
     */
    JTable jTable;
    /**
     * The Clients.
     */
    ArrayList<Client> clients;
    private  ArrayList<Order> orderList;

    /**
     * Gets orders.
     *
     * @return the orders
     */
    public HashMap<Order, HashSet<MenuItem>> getOrders() {
        return orders;
    }

    /**
     * Sets orders.
     *
     * @param orders the orders
     */
    public void setOrders(HashMap<Order, HashSet<MenuItem>> orders) {
        this.orders = orders;
    }

    /**
     * Gets menu items.
     *
     * @return the menu items
     */
    public HashSet<MenuItem> getMenuItems() {
        return menuItems;
    }


    /**
     * Find item by name menu item.
     *
     * @param name the name
     * @return the menu item
     */
    public MenuItem findItemByName(String name) {
        for (MenuItem menuItem : menuItems) {
            if (menuItem.getNameMenuItem().equals(name)) {
                return menuItem;
            }
        }
        return null;
    }

    /**
     * Instantiates a new Delivery service.
     */
    public DeliveryService() {
        orders = new HashMap<>();
        menuItems = new HashSet<>();
        clients=new ArrayList<>();
        orderList=new ArrayList<>();
    }
    @Override
    public void notifyObservers(Object arg) {
        setChanged();
        super.notifyObservers(arg);
    }

    /**
     * Well formed for orders boolean.
     *
     * @return boolean
     * @invariant wellFormedForOrders
     */
    public boolean wellFormedForOrders(){
        return true;
    }

    /**
     * Well formed for menu items boolean.
     *
     * @return boolean
     * @invariant wellFormedForMenuItems
     */
    public boolean wellFormedForMenuItems()
    {
        return menuItems != null;
    }
    @Override
    public HashSet<MenuItem> createMenuItem(MenuItem menuItem) { //create product
        assert menuItem!=null;
        assert wellFormedForMenuItems():"The menu item is not well formed";
        assert wellFormedForOrders():"The order is not well formed";
        int preCondSize=menuItems.size();
        menuItems.add(menuItem);
        int postCondSize= menuItems.size();
        assert preCondSize+1==postCondSize:"The item has not been added";
        return menuItems;
    }

    @Override
    public HashSet<MenuItem> deleteMenuItem(MenuItem menuItem) { //delete product
        assert menuItem!=null;
        assert wellFormedForMenuItems():"The menu item is not well formed";
        assert wellFormedForOrders():"The order is not well formed";
        int preCondSize=menuItems.size();
        menuItems.remove(menuItem);
       int postCondSize= menuItems.size();
        assert preCondSize==postCondSize+1:"The item has not been added";
        return menuItems;
    }

    @Override
    public HashSet<MenuItem>  modifyMenuItem(MenuItem menuItem) throws IOException { //modify product
        assert wellFormedForMenuItems():"The item is null";
        assert wellFormedForOrders():"Not well formed";
        assert menuItems.contains(menuItem):"The menu item is not in the menu";
        int priceAfter=menuItem.getPrice();
        int price=0;
        String name=menuItem.getNameMenuItem();
        for (MenuItem currMenuItem:menuItems) {
            if(currMenuItem.getNameMenuItem().equals(name))
            {
                currMenuItem.setPrice(menuItem.getPrice());
                price= currMenuItem.getPrice();
            }
        }
        for (MenuItem currMenuItem:menuItems) {
                currMenuItem.setPrice(price);
        }
        assert wellFormedForMenuItems():"The item is null";
        assert wellFormedForOrders():"Not well formed";
        return menuItems;
    }
    @Override
    public Collection<Order> report1(HashMap<Order, HashSet<MenuItem>> orders, int startHour, int endHour) {
        return orders.keySet().stream().filter(e -> e.getHours() >= startHour && e.getHours() <= endHour).collect(Collectors.toList());
    }
    @Override
    public Collection<MenuItem> report2Final(int inputMinOrder) {
        return report2(orders, inputMinOrder);
    }

    @Override
    public Collection<MenuItem> report2(HashMap<Order, HashSet<MenuItem>> orders, int inputMinOrder) {
        Collection<MenuItem> products = new ArrayList<>();
        orders.values().forEach(products::addAll);
        return products.stream().filter(e -> products.stream().filter(e::equals).count() >= inputMinOrder).distinct().collect(Collectors.toList());
    }

    /**
     * Report 3 final collection.
     *
     * @param inputMinOrders the input min orders
     * @param inputMinAmount the input min amount
     * @return the collection
     */
    public Collection<Integer> report3Final(int inputMinOrders, double inputMinAmount) {
        return report3(orders, inputMinOrders, inputMinAmount);
    }

    @Override
    public Collection<Integer> report3(HashMap<Order, HashSet<MenuItem>> orders, int inputMinOrders, double inputMinAmount) {
        Collection<Integer> clients = new ArrayList<>();
        orders.keySet().stream().filter(e -> orders.keySet().stream().filter(p -> p.getClientId()==(e.getClientId()) && orders.get(p).stream()
                .mapToDouble(MenuItem::computePrice).sum() >= inputMinAmount).count() >= inputMinOrders).forEach(e ->
        {if(!clients.contains(e.getClientId())) clients.add(e.getClientId());});
        return clients;
    }

    /**
     * Report 4 final hash map.
     *
     * @param day the day
     * @return the hash map
     */
    public HashMap<MenuItem, Integer> report4Final(int day) {
        return report4(orders, day);
    }

    @Override
    public HashMap<MenuItem, Integer> report4(HashMap<Order, HashSet<MenuItem>> orders, int day) {
        HashMap<MenuItem, Integer> map = new HashMap<>();
        orders.entrySet().stream().filter(e -> {System.out.println(e.getKey().getDay() + " " + day);
        return e.getKey().getDay() == day;}).forEach(e -> e.getValue().forEach(p -> {
            if (map.containsKey(p))
                map.replace(p, map.get(p) + 1);
            else
                map.put(p, 1);
        }));
        return map;
    }
    @Override
    public int computePriceForClientOrder(Order order) {
        assert order!=null;
        assert wellFormedForOrders():"The order not well formed";
        assert wellFormedForMenuItems():"The menu item is not well formed";
        int price=0;

        for (MenuItem orderedItem : orders.get(order)) {
            price += orderedItem.getPrice();
        }
        assert price>0: "The price must be a positive value";
        return price;
    }

    /**
     * Add order to list string builder.
     *
     * @param order the order
     * @return the string builder
     */
    public StringBuilder addOrderToList(Order order)
    {
        assert order==null:"Order is null";
        orderList.add(order);
        StringBuilder stringBuilder=new StringBuilder();
        assert false;
        stringBuilder.append("Order ID: "+order.getOrderId()+"\n");
        stringBuilder.append("Client ID: "+order.getClientId()+"\n");
        stringBuilder.append("Date: ").append(order.getDay()).append("/").append(order.getMonth()).append("/").append(order.getYear()).append("\n");
        stringBuilder.append("Total price: "+computePriceForClientOrder(order)+"\n");
        stringBuilder.append("Time:"+ LocalTime.now() +"\n");
        return stringBuilder;
    }
    @Override
    public void createOrderCleint(Order order, HashSet<MenuItem> menuItemHashSet) {
        assert wellFormedForMenuItems():"The menu item is not well formed";
        assert wellFormedForOrders():"The order is not well formed";
        assert order!=null;
        orders.put(order,menuItemHashSet);

    }

    @Override
    public void generateBill(String string) {
        assert string!=null;
        FileWriter fileWriter=new FileWriter();
        fileWriter.createFile();
        fileWriter.writeFile(string);
    }




    @Override
    public HashSet<MenuItem>  importProducts() {
        try {
            Files.lines(Paths.get("myProducts.csv"))
                    .skip(1).distinct().forEach((line) -> {
                BaseProduct item = new BaseProduct();
                String[] elem = line.split(",");
                item.setNameMenuItem(elem[0]);
                item.setRating( Double.parseDouble(elem[1]));
                item.setCalories( Integer.parseInt(elem[2]));
                item.setProtein( Integer.parseInt(elem[3]));
                item.setFat( Integer.parseInt(elem[4]));
                item.setSodium( Integer.parseInt(elem[5]));
                item.setPrice( Integer.parseInt(elem[6]));
                boolean exists=false;
                for (MenuItem menuItem:menuItems) {
                    if(menuItem.getNameMenuItem().equals(item.getNameMenuItem()))
                    {
                        exists=true;
                        break;
                    }
                }
                if(!exists)
                    menuItems.add(item);
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return menuItems;
    }

    @Override
    public CompositeProduct createComposite(String name, ArrayList<MenuItem> itemIngredients) {
        CompositeProduct compositeProduct=new CompositeProduct();
        double finalRating=0.0;
        int finalCalories=0;
        int finalProtein=0;
        int finalFat=0;
        int finalSodium=0;
        int finalPrice=0;
        for (MenuItem currMenuItem:itemIngredients) {
            finalPrice+=currMenuItem.getPrice();
            finalRating+= currMenuItem.getRating();
            finalCalories+= currMenuItem.getCalories();
            finalFat+= currMenuItem.getFat();
            finalSodium+= currMenuItem.getSodium();
        }
        finalRating/= itemIngredients.size();
        compositeProduct.setNameMenuItem(name);
        compositeProduct.setRating(finalRating);
        compositeProduct.setCalories(finalCalories);
        compositeProduct.setProtein(finalProtein);
        compositeProduct.setFat(finalFat);
        compositeProduct.setSodium(finalSodium);
        compositeProduct.setPrice(finalPrice);
        compositeProduct.setCompositeProducts(itemIngredients);
        return compositeProduct;
    }

    /**
     * Print menu item.
     *
     * @param baseProduct the base product
     * @throws IllegalAccessException the illegal access exception
     */
    public void printMenuItem(BaseProduct baseProduct) throws IllegalAccessException {
        Field[] fields=baseProduct.getClass().getDeclaredFields();
        for (Field field:fields) {
            field.setAccessible(true);
            String fieldType=field.getType().getSimpleName();
            if(fieldType.equals("String"))
            {
                String fieldValue=(String) field.get(baseProduct);
                System.out.println("Title: "+fieldValue);
            }
           else
               if(fieldType.equals("int"))
               {
                   String fieldName=field.getName();
                   if(fieldName.equals("calories"))
                       System.out.println("Calories: "+baseProduct.getCalories());
                   else
                       if(fieldName.equals("protein"))
                           System.out.println("Protein: "+baseProduct.getProtein());
                       else if(fieldName.equals("fat"))
                            System.out.println("Fat: "+ baseProduct.getFat());
                       else
                           if(fieldName.equals("sodium"))
                            System.out.println("Sodium: "+baseProduct.getSodium());
                           else
                               if(fieldName.equals("price"))
                                   System.out.println("Price: "+baseProduct.getPrice());
               }
               else
                   if(fieldType.equals("double"))
                       System.out.println("Rating: "+baseProduct.getRating());
        }
    }

    /**
     * Create j table.
     *
     * @param menuItems the menu items
     * @return the j table
     * @throws IllegalAccessException the illegal access exception
     */
    public  JTable create(HashSet<MenuItem>  menuItems) throws IllegalAccessException {
        BaseProduct baseProduct=new BaseProduct();
        String[] columnsTable=new String[baseProduct.getClass().getSuperclass().getDeclaredFields().length];
        int i=0;
        for(Field field:baseProduct.getClass().getSuperclass().getDeclaredFields())
        {
            columnsTable[i++]= field.getName();
        }
        DefaultTableModel defaultTableModel=new DefaultTableModel();
        defaultTableModel.setColumnIdentifiers(columnsTable);
        for (Object currObject:menuItems) {
            Vector<Object> genericListObject=new Vector<>();
            genericListObject.setSize(baseProduct.getClass().getSuperclass().getDeclaredFields().length);
            int currColumnToAddTo=0;
            for (Field field:currObject.getClass().getSuperclass().getDeclaredFields()) {
                field.setAccessible(true);
                genericListObject.set(currColumnToAddTo,field.get(currObject));
                currColumnToAddTo++;
            }
            defaultTableModel.addRow(genericListObject);
        }
        jTable=new JTable(defaultTableModel);
        return  jTable;
    }

    /**
     * Create for order j table.
     *
     * @param order the order
     * @return the j table
     * @throws IllegalAccessException the illegal access exception
     */
    public  JTable createForOrder(Order order) throws IllegalAccessException {
        String[] columnsTable=new String[order.getClass().getDeclaredFields().length];
        int i=0;
        for(Field field:order.getClass().getDeclaredFields())
        {
            columnsTable[i++]= field.getName();
        }
        DefaultTableModel defaultTableModel=new DefaultTableModel();
        defaultTableModel.setColumnIdentifiers(columnsTable);
        for (Object currObject:orders.get(order)) {
            Vector<Object> genericListObject=new Vector<>();
            genericListObject.setSize(order.getClass().getDeclaredFields().length);
            int currColumnToAddTo=0;
            for (Field field:order.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                genericListObject.set(currColumnToAddTo,field.get(order));
                currColumnToAddTo++;
            }
            defaultTableModel.addRow(genericListObject);
        }
        jTable=new JTable(defaultTableModel);
        return  jTable;
    }

    /**
     * Add to clients list.
     *
     * @param name the name
     * @param pass the pass
     */
    public void addToClientsList(String name,String pass)
    {
        assert name!=null;
        assert pass !=null;
        int preCondSize=clients.size();
        clients.add(new Client(name,pass));
        int postCondSize=clients.size();
        assert preCondSize+1==postCondSize:"The client has not been added";
    }

    /**
     * Gets clients.
     *
     * @return the clients
     */
    public ArrayList<Client> getClients() {
        return clients;
    }

    /**
     * Import products general hash set.
     *
     * @return the hash set
     */
    public HashSet<MenuItem>  importProductsGeneral() {
        try {
            Files.lines(Paths.get("products2.csv"))
                    .skip(1).distinct().forEach((line) -> {
                BaseProduct item = new BaseProduct();
                String[] elem = line.split(",");
                item.setNameMenuItem(elem[0]);
                item.setRating( Double.parseDouble(elem[1]));
                item.setCalories( Integer.parseInt(elem[2]));
                item.setProtein( Integer.parseInt(elem[3]));
                item.setFat( Integer.parseInt(elem[4]));
                item.setSodium( Integer.parseInt(elem[5]));
                item.setPrice( Integer.parseInt(elem[6]));
                int exists=0;
                for (MenuItem menuItem:menuItems) {
                    if(menuItem.getNameMenuItem().equals(item.getNameMenuItem()))
                    {
                        exists=1;
                        break;
                    }
                }
                if(exists==0)
                    menuItems.add(item);
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return menuItems;
    }

}
