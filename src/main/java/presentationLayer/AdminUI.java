package presentationLayer;

import businessLayer.*;
import businessLayer.MenuItem;
import dataLayer.FileWriter;
import dataLayer.Serializer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import javax.swing.*;


/**
 * The type Admin ui.
 */
public class AdminUI  {

    private JButton createButton=new JButton("Create Item");
    private JButton addButton=new JButton("Add Product");
    private JButton addCompositeButton=new JButton("Add Composite");
    private JButton deleteButton=new JButton("Delete Product");
    private JButton modifyButton=new JButton("Modify Product");
    private JButton backButton=new JButton("Go Back");
    private JButton importProductsButton=new JButton("Import Products");
    private JButton closeButton=new JButton("Close");
    private JButton showButton=new JButton("Show Menu");
    private JButton createReportButton=new JButton("Create Report");
    private JButton generateReportButton=new JButton("Generate Report 1");
    private JButton generateReport2Button=new JButton("Generate Report 2");
    private JButton generateReport3Button=new JButton("Generate Report 3");
    private JButton generateReport4Button=new JButton("Generate Report 4");
    /**
     * The Item name label.
     */
    public JLabel itemNameLabel=new JLabel("Item Name:");
    /**
     * The Item price label.
     */
    public JLabel itemPriceLabel=new JLabel("Item Price:");
    /**
     * The Item ingredients label.
     */
    public JLabel itemIngredientsLabel=new JLabel("Item Ingredients:");
    /**
     * The Rating label.
     */
    public JLabel ratingLabel=new JLabel("Rating:");
    /**
     * The Calories label.
     */
    public JLabel caloriesLabel=new JLabel("Calories:");
    /**
     * The Protein label.
     */
    public JLabel proteinLabel=new JLabel("Protein:");
    /**
     * The Fat label.
     */
    public JLabel fatLabel=new JLabel("Fat:");
    /**
     * The Sodium label.
     */
    public JLabel sodiumLabel=new JLabel("Sodium:");
    /**
     * The Start time label.
     */
    public JLabel startTimeLabel=new JLabel("Start Hour:");
    /**
     * The End time label.
     */
    public JLabel endTimeLabel=new JLabel("End Hour:");
    /**
     * The No of times label.
     */
    public JLabel noOfTimesLabel=new JLabel("Nr of times:");
    /**
     * The Amount label.
     */
    public JLabel amountLabel=new JLabel("Amount:");
    /**
     * The Specified day label.
     */
    public JLabel specifiedDayLabel=new JLabel("Day:");
    /**
     * The Item name text.
     */
    public JTextField itemNameText=new JTextField();
    /**
     * The Item price text.
     */
    public JTextField itemPriceText=new JTextField();
    /**
     * The Item ingredients text.
     */
    public JTextField itemIngredientsText=new JTextField();
    /**
     * The Rating text.
     */
    public JTextField ratingText=new JTextField();
    /**
     * The Calories text.
     */
    public JTextField caloriesText=new JTextField();
    /**
     * The Protein text.
     */
    public JTextField proteinText=new JTextField();
    /**
     * The Fat text.
     */
    public JTextField fatText=new JTextField();
    /**
     * The Sodium text.
     */
    public JTextField sodiumText=new JTextField();
    /**
     * The Start time text.
     */
    public JTextField startTimeText=new JTextField();
    /**
     * The End time text.
     */
    public JTextField endTimeText=new JTextField();
    /**
     * The No of times text.
     */
    public JTextField noOfTimesText=new JTextField();
    /**
     * The Amount text.
     */
    public JTextField amountText=new JTextField();
    /**
     * The Specified day text.
     */
    public JTextField specifiedDayText=new JTextField();
    /**
     * The My scroll pane 1.
     */
    JScrollPane myScrollPane1;
    /**
     * The J table.
     */
    JTable jTable;
    /**
     * The Delivery service.
     */
    DeliveryService deliveryService;
    /**
     * The Menu items.
     */
    HashSet<MenuItem> menuItems=new HashSet<>();
    /**
     * The Item ingredients.
     */
    ArrayList<MenuItem> itemIngredients=new ArrayList<>();

    /**
     * Instantiates a new Admin ui.
     *
     * @param deliveryService the delivery service
     */
    public AdminUI( DeliveryService deliveryService)
    {
        this.deliveryService=deliveryService;
        JFrame frame = new JFrame();
        frame.setSize(750,700);
       // frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        JPanel panel=new JPanel();
        panel.setBackground(Color.decode("#ccd9ff"));
        panel.setLayout(null);
        frame.add(panel);
        itemNameLabel.setBounds(7,7,80,20);
        itemNameText.setBounds(100,7,90,20);
        itemPriceLabel.setBounds(7,47,80,20);
        itemPriceText.setBounds(100,47,90,20);

        ratingLabel.setBounds(555,7,90,20);
        caloriesLabel.setBounds(555,47,90,20);
        proteinLabel.setBounds(555,87,90,20);
        fatLabel.setBounds(555,127,90,20);
        sodiumLabel.setBounds(555,167,90,20);
        ratingText.setBounds(605,7,90,20);
        caloriesText.setBounds(610,47,90,20);
        proteinText.setBounds(610,87,90,20);
        fatText.setBounds(610,127,90,20);
        sodiumText.setBounds(610,167,90,20);

        itemIngredientsLabel.setBounds(7,87,120,20);
        itemIngredientsText.setBounds(120,87,90,20);
        backButton.setBounds(555,630,165,25);
        closeButton.setBounds(7,630,165,25);
        addButton.setBounds(7,127,120,25);
        addCompositeButton.setBounds(137,127,120,25);
        deleteButton.setBounds(7,167,120,25);
        modifyButton.setBounds(7,207,120,25);
        importProductsButton.setBounds(137,167,130,25);
        showButton.setBounds(137,207,130,25);
        createReportButton.setBounds(67,247,130,25);


        itemNameLabel.setFont(new Font("Serif",Font.BOLD,15));
        itemPriceLabel.setFont(new Font("Serif",Font.BOLD,15));
        itemIngredientsLabel.setFont(new Font("Serif",Font.BOLD,15));

        importProductsButtonActionListenerProduct(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JScrollPane myScrollPane3 = new JScrollPane();
                myScrollPane3.setBounds(7, 320, 500, 300);
                JTable jTable3=new JTable();
                menuItems=deliveryService.importProductsGeneral();
                try {
                    jTable3= deliveryService.create(menuItems);
                } catch (IllegalAccessException illegalAccessException) {
                    illegalAccessException.printStackTrace();
                }
                jTable3.setEnabled(true);
                jTable3.setVisible(true);
                myScrollPane3.setViewportView(jTable3);
                panel.add(myScrollPane3);
            }
        });
        addButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    String inputNameText = itemNameText.getText();
                    int inputPriceText = Integer.parseInt(itemPriceText.getText());
                    double inputRatingText = Double.parseDouble(ratingText.getText());
                    int inputCaloriesText = Integer.parseInt(caloriesText.getText());
                    int inputProteinText = Integer.parseInt(proteinText.getText());
                    int inputFatText = Integer.parseInt(fatText.getText());
                    int inputSodiumText = Integer.parseInt(sodiumText.getText());
                    BaseProduct baseProduct = new BaseProduct();
                    baseProduct.setNameMenuItem(inputNameText);
                    baseProduct.setRating(inputRatingText);
                    baseProduct.setCalories(inputCaloriesText);
                    baseProduct.setProtein(inputProteinText);
                    baseProduct.setFat(inputFatText);
                    baseProduct.setPrice(inputPriceText);
                    baseProduct.setSodium(inputSodiumText);
                    deliveryService.importProducts();
                    menuItems = deliveryService.createMenuItem(baseProduct);
                    try {
                         JScrollPane myScrollPane1 = new JScrollPane();
                        myScrollPane1.setBounds(7, 320, 500, 300);
                         JTable jTable = deliveryService.create(menuItems);
                        jTable.setEnabled(true);
                        jTable.setVisible(true);
                        myScrollPane1.setViewportView(jTable);
                        panel.add(myScrollPane1);
                    } catch (IllegalAccessException illegalAccessException) {
                        illegalAccessException.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(null, "Added successfully!");

            }
        });
        deleteButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputNameText=itemNameText.getText();
                int inputPriceText=Integer.parseInt(itemPriceText.getText());
                double inputRatingText=Double.parseDouble(ratingText.getText());
                int inputCaloriesText=Integer.parseInt(caloriesText.getText());
                int inputProteinText=Integer.parseInt(proteinText.getText());
                int inputFatText=Integer.parseInt(fatText.getText());
                int inputSodiumText=Integer.parseInt(sodiumText.getText());
                BaseProduct baseProduct = new BaseProduct();
                baseProduct.setNameMenuItem(inputNameText);
                baseProduct.setRating(inputRatingText);
                baseProduct.setCalories(inputCaloriesText);
                baseProduct.setProtein(inputProteinText);
                baseProduct.setFat(inputFatText);
                baseProduct.setPrice(inputPriceText);
                baseProduct.setSodium(inputSodiumText);
                //menuItems=deliveryService.importProducts();
                menuItems=deliveryService.deleteMenuItem(baseProduct);
                System.out.println("at delete "+ deliveryService.getMenuItems().contains(baseProduct));
                try {
                   JScrollPane myScrollPane4 = new JScrollPane();
                    myScrollPane4.setBounds(7, 320, 500, 300);
                      JTable jTable4=deliveryService.create(menuItems);
                    jTable4.setEnabled(true);
                    jTable4.setVisible(true);
                    myScrollPane4.setViewportView(jTable4);
                    panel.add(myScrollPane4);
                } catch (IllegalAccessException illegalAccessException) {
                    illegalAccessException.printStackTrace();
                }
                JOptionPane.showMessageDialog(null,"Deleted successfully!");

            }
        });
        modifyButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputNameText=itemNameText.getText();
                int inputPriceText=Integer.parseInt(itemPriceText.getText());
                double inputRatingText=Double.parseDouble(ratingText.getText());
                int inputCaloriesText=Integer.parseInt(caloriesText.getText());
                int inputProteinText=Integer.parseInt(proteinText.getText());
                int inputFatText=Integer.parseInt(fatText.getText());
                int inputSodiumText=Integer.parseInt(sodiumText.getText());
                BaseProduct baseProduct = new BaseProduct();
                baseProduct.setNameMenuItem(inputNameText);
                baseProduct.setRating(inputRatingText);
                baseProduct.setCalories(inputCaloriesText);
                baseProduct.setProtein(inputProteinText);
                baseProduct.setFat(inputFatText);
                baseProduct.setPrice(inputPriceText);
                baseProduct.setSodium(inputSodiumText);
                 deliveryService.importProducts();
                menuItems=deliveryService.createMenuItem(baseProduct);
                JOptionPane.showMessageDialog(null,"The modification has been made!");
                //System.out.println("The product exists? " + deliveryService.getMenuItems().contains(baseProduct));
                try {
                    JScrollPane myScrollPane1 = new JScrollPane();
                    myScrollPane1.setBounds(7, 320, 500, 300);
                    JTable jTable=new JTable();
                    jTable=deliveryService.create(menuItems);
                    jTable.setEnabled(true);
                    jTable.setVisible(true);
                    myScrollPane1.setViewportView(jTable);
                    panel.add(myScrollPane1);
                } catch (IllegalAccessException illegalAccessException) {
                    illegalAccessException.printStackTrace();
                }
            }
        });
        closeButtonActionListenerProduct(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // try {
                try {
                    Serializer.serialize(deliveryService);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                // } catch (IOException ioException) {
                  //  ioException.printStackTrace();
               // }
                JOptionPane.showMessageDialog(null,"The data was successfully deserialized!");
            }
        });
        addCompositeButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] ingredients = itemIngredientsText.getText().split(",");
                for (String currString:ingredients) {
                    System.out.println(currString);
                    MenuItem baseProduct=new BaseProduct();
                    baseProduct=deliveryService.findItemByName(currString);
                    itemIngredients.add(baseProduct);
                    baseProduct=deliveryService.createComposite(itemNameText.getText(),itemIngredients);
                    menuItems=deliveryService.createMenuItem(baseProduct);
                    ratingText.setText("");
                    itemPriceText.setText("");
                    caloriesText.setText("");
                    fatText.setText("");
                    proteinText.setText("");
                    sodiumText.setText("");
                    try {
                         JScrollPane myScrollPane2 = new JScrollPane();
                        myScrollPane2.setBounds(7, 320, 500, 300);
                       JTable jTable1 = deliveryService.create(menuItems);
                        jTable1.setEnabled(true);
                        jTable1.setVisible(true);
                        myScrollPane2.setViewportView(jTable1);
                        panel.add(myScrollPane2);
                    } catch (IllegalAccessException illegalAccessException) {
                        illegalAccessException.printStackTrace();
                    }
                }

            }
        });
        showButtonActionListenerProduct(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuItems=deliveryService.importProducts();
                try {
                    myScrollPane1 = new JScrollPane();
                    myScrollPane1.setBounds(7, 320, 500, 300);
                    jTable=deliveryService.create(menuItems);
                    jTable.setEnabled(true);
                    jTable.setVisible(true);
                    myScrollPane1.setViewportView(jTable);
                    panel.add(myScrollPane1);
                } catch (IllegalAccessException illegalAccessException) {
                    illegalAccessException.printStackTrace();
                }
                System.out.println(deliveryService.getMenuItems());
            }
        });
        backButtonActionListenerProduct(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });

        createReportButtonActionListenerProduct(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frameReport=new JFrame();
                frameReport.setSize(300,450);
                frameReport.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                JPanel panelReport=new JPanel();
                panelReport.setBackground(Color.decode("#ccd9ff"));
                panelReport.setLayout(null);
                frameReport.add(panelReport);
                startTimeLabel.setBounds(7,47,120,25);
                startTimeText.setBounds(100,47,120,25);
                endTimeLabel.setBounds(7,87,120,25);
                endTimeText.setBounds(100,87,120,25);
                noOfTimesLabel.setBounds(7,127,120,25);
                noOfTimesText.setBounds(100,127,120,25);
                amountLabel.setBounds(7,167,120,25);
                amountText.setBounds(100,167,120,25);
                specifiedDayLabel.setBounds(7,207,120,25);
                specifiedDayText.setBounds(100,207,120,25);


                generateReportButton.setBounds(67,247,140,30);
                generateReport2Button.setBounds(67,287,140,30);
                generateReport3Button.setBounds(67,327,140,30);
                generateReport4Button.setBounds(67,367,140,30);
                panelReport.add(startTimeLabel);
                panelReport.add(startTimeText);
                panelReport.add(endTimeLabel);
                panelReport.add(endTimeText);
                panelReport.add(generateReportButton);
                panelReport.add(noOfTimesLabel);
                panelReport.add(noOfTimesText);
                panelReport.add(amountLabel);
                panelReport.add(amountText);
                panelReport.add(specifiedDayLabel);
                panelReport.add(specifiedDayText);
                panelReport.add(generateReport2Button);
                panelReport.add(generateReport3Button);
                panelReport.add(generateReport4Button);
                frameReport.setVisible(true);
            }
        });
        generateReportButtonActionListenerProduct(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             int startHour=Integer.parseInt(startTimeText.getText());
            int endHour=Integer.parseInt(endTimeText.getText());
            Collection<Order> orders1=new ArrayList<>();
            orders1=deliveryService.report1(deliveryService.getOrders(),startHour,endHour);
           dataLayer.FileWriter fileWriter=new FileWriter();
           fileWriter.createFileForReport("report1.txt");
           StringBuilder stringBuilder=new StringBuilder();
           stringBuilder.append("ORDERS PERFORMED IN A CERTAIN INTERVAL"+"\n");
           stringBuilder.append("-------------------------------------------"+"\n\n\n");
           fileWriter.writeFileForReport(stringBuilder.toString(),"report1.txt");
                for (Order currOrder:orders1) {
                    String toString=new String();
                    toString=currOrder.toString();
                    stringBuilder.append(toString);
                    fileWriter.writeFileForReport(stringBuilder.toString(),"report1.txt");
                }
                stringBuilder.append("-------------------------------------"+"\n");
                stringBuilder.append("END OF REPORT");
                fileWriter.writeFileForReport(stringBuilder.toString(),"report1.txt");

            }
        });
        generateReport2ButtonActionListenerProduct(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int noOfTimes=Integer.parseInt(noOfTimesText.getText());
                Collection<MenuItem> menuItemCollection=new ArrayList<>();
                menuItemCollection=deliveryService.report2Final(noOfTimes);
                dataLayer.FileWriter fileWriter=new FileWriter();
                fileWriter.createFileForReport("report2.txt");
                StringBuilder stringBuilder=new StringBuilder();
                stringBuilder.append("PRODUCTS ORDERED MORE THAN A SPECIFIED NUMBER OF TIMES"+"\n");
                stringBuilder.append("-------------------------------------------"+"\n\n\n");
                fileWriter.writeFileForReport(stringBuilder.toString(),"report2.txt");
                for (MenuItem currMenuItem:menuItemCollection) {
                    String toString=new String();
                    toString=currMenuItem.toString();
                    stringBuilder.append(toString);
                    fileWriter.writeFileForReport(stringBuilder.toString(),"report2.txt");
                }
                stringBuilder.append("-------------------------------------"+"\n");
                stringBuilder.append("END OF REPORT");
                fileWriter.writeFileForReport(stringBuilder.toString(),"report2.txt");
            }
        });
        generateReport3ButtonActionListenerProduct(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int noOfTimes=Integer.parseInt(noOfTimesText.getText());
                int amount=Integer.parseInt(amountText.getText());
                Collection<Integer> menuItemCollection1=new ArrayList<>();
                menuItemCollection1=deliveryService.report3Final(noOfTimes,amount);
                dataLayer.FileWriter fileWriter=new FileWriter();
                fileWriter.createFileForReport("report2.txt");
                StringBuilder stringBuilder=new StringBuilder();
                stringBuilder.append("CLIENTS THAT HAVE ORDERED MORE THAN A SPECIFIED NUMBER OF TIMES AND THE VALUE OF THE ORDER IS HIGHER THAN A SPECIFIED AMOUNT"+"\n");
                stringBuilder.append("-------------------------------------------"+"\n\n\n");
                fileWriter.writeFileForReport(stringBuilder.toString(),"report3.txt");
                for (Integer currInteger:menuItemCollection1) {
                    stringBuilder.append("Client ID:");
                    String toString=new String();
                    toString=currInteger.toString();
                    stringBuilder.append(toString+"\n");
                    fileWriter.writeFileForReport(stringBuilder.toString(),"report3.txt");
                }
                stringBuilder.append("\n-------------------------------------"+"\n");
                stringBuilder.append("END OF REPORT");
                fileWriter.writeFileForReport(stringBuilder.toString(),"report3.txt");
            }
        });
        generateReport4ButtonActionListenerProduct(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int specifiedDay=Integer.parseInt(specifiedDayText.getText());
                HashMap<MenuItem, Integer> menuItemCollection2=new HashMap<>();
                menuItemCollection2=deliveryService.report4Final(specifiedDay);
                dataLayer.FileWriter fileWriter=new FileWriter();
                fileWriter.createFileForReport("report2.txt");
                StringBuilder stringBuilder=new StringBuilder();
                stringBuilder.append("PRODUCTS ORDERED WITHIN A SPECIFIED DAY"+"\n");
                stringBuilder.append("-------------------------------------------"+"\n\n\n");
                fileWriter.writeFileForReport(stringBuilder.toString(),"report4.txt");
                for (MenuItem currMenuItem:menuItemCollection2.keySet()) {
                    String toString=new String();
                    toString=currMenuItem.toString();
                    stringBuilder.append(toString+"\n");
                    fileWriter.writeFileForReport(stringBuilder.toString(),"report4.txt");
                }
                stringBuilder.append("\n-------------------------------------"+"\n");
                stringBuilder.append("END OF REPORT");
                fileWriter.writeFileForReport(stringBuilder.toString(),"report4.txt");
            }
        });
        panel.add(itemNameLabel);
        panel.add(itemNameText);
        panel.add(itemPriceLabel);
        panel.add(itemPriceText);
        panel.add(itemIngredientsLabel);
        panel.add(itemIngredientsText);
        panel.add(addButton);
        panel.add(deleteButton);
        panel.add(modifyButton);
        panel.add(backButton);
        panel.add(importProductsButton);
        panel.add(showButton);
        panel.add(createReportButton);
        panel.add(closeButton);
        panel.add(ratingText);
        panel.add(ratingLabel);
        panel.add(caloriesLabel);
        panel.add(caloriesText);
        panel.add(proteinLabel);
        panel.add(proteinText);
        panel.add(fatLabel);
        panel.add(fatText);
        panel.add(sodiumLabel);
        panel.add(sodiumText);
        panel.add(addCompositeButton);
        frame.setVisible(true);
    }

    /**
     * Create button action listener.
     *
     * @param actionListener the action listener
     */
    public void createButtonActionListener(final ActionListener actionListener)
    {
        createButton.addActionListener(actionListener);
    }

    /**
     * Add button action listener.
     *
     * @param actionListener the action listener
     */
    public void addButtonActionListener(final ActionListener actionListener)
    {
        addButton.addActionListener(actionListener);
    }

    /**
     * Add composite button action listener.
     *
     * @param actionListener the action listener
     */
    public void addCompositeButtonActionListener(final ActionListener actionListener)
    {
        addCompositeButton.addActionListener(actionListener);
    }

    /**
     * Delete button action listener.
     *
     * @param actionListener the action listener
     */
    public void deleteButtonActionListener(final ActionListener actionListener)
    {
        deleteButton.addActionListener(actionListener);
    }

    /**
     * Modify button action listener.
     *
     * @param actionListener the action listener
     */
    public void modifyButtonActionListener(final ActionListener actionListener)
    {
        modifyButton.addActionListener(actionListener);
    }

    /**
     * Back button action listener product.
     *
     * @param actionListener the action listener
     */
    public void backButtonActionListenerProduct(final ActionListener actionListener)
    {
        backButton.addActionListener(actionListener);
    }

    /**
     * Import products button action listener product.
     *
     * @param actionListener the action listener
     */
    public void importProductsButtonActionListenerProduct(final ActionListener actionListener)
    {
        importProductsButton.addActionListener(actionListener);
    }

    /**
     * Close button action listener product.
     *
     * @param actionListener the action listener
     */
    public void closeButtonActionListenerProduct(final ActionListener actionListener)
    {
        closeButton.addActionListener(actionListener);
    }

    /**
     * Show button action listener product.
     *
     * @param actionListener the action listener
     */
    public void showButtonActionListenerProduct(final ActionListener actionListener)
    {
        showButton.addActionListener(actionListener);
    }

    /**
     * Create report button action listener product.
     *
     * @param actionListener the action listener
     */
    public void createReportButtonActionListenerProduct(final ActionListener actionListener)
    {
        createReportButton.addActionListener(actionListener);
    }

    /**
     * Generate report button action listener product.
     *
     * @param actionListener the action listener
     */
    public void generateReportButtonActionListenerProduct(final ActionListener actionListener)
    {
        generateReportButton.addActionListener(actionListener);
    }

    /**
     * Generate report 2 button action listener product.
     *
     * @param actionListener the action listener
     */
    public void generateReport2ButtonActionListenerProduct(final ActionListener actionListener)
    {
        generateReport2Button.addActionListener(actionListener);
    }

    /**
     * Generate report 3 button action listener product.
     *
     * @param actionListener the action listener
     */
    public void generateReport3ButtonActionListenerProduct(final ActionListener actionListener)
    {
        generateReport3Button.addActionListener(actionListener);
    }

    /**
     * Generate report 4 button action listener product.
     *
     * @param actionListener the action listener
     */
    public void generateReport4ButtonActionListenerProduct(final ActionListener actionListener)
    {
        generateReport4Button.addActionListener(actionListener);
    }
  
}