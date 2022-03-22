package presentationLayer;

import businessLayer.BaseProduct;
import businessLayer.DeliveryService;
import businessLayer.MenuItem;
import businessLayer.Order;
import dataLayer.Serializer;

import javax.sound.midi.Soundbank;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;

/**
 * The type Client ui.
 */
public class ClientUI {

    private JLabel idOrderLabel=new JLabel("Order Id:");
    private JLabel idClientLabel=new JLabel("Client Id:");
    private JLabel dateOrderLabel=new JLabel("Order Date:");

    private JTextField idOrderText=new JTextField();
    private JTextField idClientText=new JTextField();
    private JTextField dateOrderText=new JTextField();

    private JButton backButtonClient=new JButton("Go Back!");
    private JButton createButtonClient=new JButton("Create Order");
    private JButton generateBillButton=new JButton("Generate Bill");
    private JButton showMenuButton=new JButton( "Show Menu");
    private JButton searchProductButton=new JButton( "Search Product");
    private JLabel chosenItemsLabel=new JLabel("Chosen Items:");
    private JTextArea chosenItemsText=new JTextArea();
    /**
     * The Menu items.
     */
    HashSet<MenuItem> menuItems=new HashSet<>();
    /**
     * The Delivery service.
     */
    DeliveryService deliveryService;
    /**
     * The My scroll pane 1.
     */
    JScrollPane myScrollPane1;
    /**
     * The J table.
     */
    JTable jTable;
    /**
     * The Order list.
     */
    ArrayList<Order> orderList = new ArrayList<Order>();
    /**
     * The Ordered items.
     */
    ArrayList<String> orderedItems=new ArrayList<>();

    /**
     * Instantiates a new Client ui.
     *
     * @param deliveryService the delivery service
     */
    public ClientUI(final DeliveryService deliveryService)
    {
        this.deliveryService=deliveryService;
        //orders=new HashMap<>();
        JFrame frame1 = new JFrame();
        frame1.setSize(750,700);
        frame1.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        JPanel panel1=new JPanel();
        panel1.setBackground(Color.decode("#ccd9ff"));
        panel1.setLayout(null);
        frame1.add(panel1);

        idOrderLabel.setBounds(7,7,80,20);
        idOrderText.setBounds(100,7,90,20);
        idClientLabel.setBounds(7,47,80,20);
        idClientText.setBounds(100,47,90,20);
        dateOrderLabel.setBounds(7,87,80,20);
        dateOrderText.setBounds(100,87,90,20);
        backButtonClient.setBounds(555,7,165,25);
        createButtonClient.setBounds(7,127,140,25);
        showMenuButton.setBounds(7,167,140,25);
        searchProductButton.setBounds(440,207,140,25);
        generateBillButton.setBounds(7,207,140,25);
        chosenItemsLabel.setBounds(450,117,140,25);
        chosenItemsText.setBounds(340,157,340,35);

        idOrderLabel.setFont(new Font("Serif",Font.BOLD,15));
        idClientLabel.setFont(new Font("Serif",Font.BOLD,15));
        dateOrderLabel.setFont(new Font("Serif",Font.BOLD,15));
        createButtonClient.setFont(new Font("Serif",Font.BOLD,15));
        backButtonClient.setFont(new Font("Serif",Font.BOLD,15));
        showMenuButton.setFont(new Font("Serif",Font.BOLD,15));
        generateBillButton.setFont(new Font("Serif",Font.BOLD,15));
        searchProductButton.setFont(new Font("Serif",Font.BOLD,15));
        chosenItemsLabel.setFont(new Font("Serif",Font.BOLD,15));

        showButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)  {

                menuItems=deliveryService.importProducts();
                try {
                     myScrollPane1 = new JScrollPane();
                    myScrollPane1.setBounds(7, 320, 500, 300);
                    jTable=deliveryService.create(menuItems);
                    jTable.setEnabled(true);
                    jTable.setVisible(true);
                    myScrollPane1.setViewportView(jTable);
                    panel1.add(myScrollPane1);
                } catch (IllegalAccessException illegalAccessException) {
                    illegalAccessException.printStackTrace();
                }

            }
        });

        createOrderButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HashSet<MenuItem> orderHashSet=new HashSet<>();
                int inputOrderId=Integer.parseInt(idOrderText.getText());
                int inputClientId=Integer.parseInt(idClientText.getText());
                String inputDateOrder=dateOrderText.getText();
                String[] input=inputDateOrder.split("-");
                Order order=new Order();
                order.setClientId(inputClientId);
                order.setOrderId(inputOrderId);
                order.setDay(Integer.parseInt(input[0]));
                order.setMonth(Integer.parseInt(input[1]));
                order.setYear(Integer.parseInt(input[2]));
                String inputChoosenItems=chosenItemsText.getText();
                String [] inputItems=inputChoosenItems.split(";");
                MenuItem inputBaseProduct=new BaseProduct();
                for (int i = 0; i <inputItems.length ; i++) {
                    String[] finalInputItems=inputItems[i].split(",");
                    orderedItems.add(finalInputItems[1]);
                    inputBaseProduct=deliveryService.findItemByName(finalInputItems[1]);
                    orderHashSet.add(inputBaseProduct);
                }


                deliveryService.createOrderCleint(order,orderHashSet);
                try {
                    JScrollPane myScrollPane1 = new JScrollPane();
                    myScrollPane1.setBounds(7, 320, 500, 300);
                    JTable jTable=deliveryService.createForOrder(order);
                    jTable.setEnabled(true);
                    jTable.setVisible(true);
                    myScrollPane1.setViewportView(jTable);
                    panel1.add(myScrollPane1);
                } catch (IllegalAccessException illegalAccessException) {
                    illegalAccessException.printStackTrace();
                }
            }
        });
        generateBillButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int orderId=Integer.parseInt(idOrderText.getText());
                Order newOrder=new Order();
                newOrder.setOrderId(orderId);
                newOrder.setClientId(Integer.parseInt(idClientText.getText()));
                String inputDateOrder=dateOrderText.getText();
                String[] input=inputDateOrder.split("-");
                newOrder.setDay(Integer.parseInt(input[0]));
                newOrder.setMonth(Integer.parseInt(input[1]));
                newOrder.setYear(Integer.parseInt(input[2]));
                if(newOrder==null)
                {
                    JOptionPane.showMessageDialog(null,"Order not found");
                }

                StringBuilder whatToInsert=deliveryService.addOrderToList(newOrder);
                for (String string:orderedItems) {
                    whatToInsert.append(string);
                    whatToInsert.append("\n");
                }
                deliveryService.generateBill(whatToInsert.toString());
                EmployeeUI employeeUI=new EmployeeUI(deliveryService);
                deliveryService.addObserver(employeeUI);
               deliveryService.notifyObservers(whatToInsert.toString());
                    JOptionPane.showMessageDialog(null,"Bill generated!");
            }
        });
        searchButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputCriteria=chosenItemsText.getText();
                HashSet<MenuItem> menuItemsCriteria=new HashSet<>();
                menuItems.stream().filter((s)->s.getNameMenuItem().startsWith(inputCriteria)).forEach(menuItemsCriteria::add);
                try {
                    myScrollPane1 = new JScrollPane();
                    myScrollPane1.setBounds(7, 320, 500, 300);
                    jTable = deliveryService.create(menuItemsCriteria);
                    jTable.setEnabled(true);
                    jTable.setVisible(true);
                    myScrollPane1.setViewportView(jTable);
                    panel1.add(myScrollPane1);
                } catch (IllegalAccessException illegalAccessException) {
                    illegalAccessException.printStackTrace();
                }
            }
        });
        goBackButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame1.setVisible(false);
            }
        });

        panel1.add(idOrderLabel);
        panel1.add(idOrderText);
        panel1.add(idClientLabel);
        panel1.add(idClientText);
        panel1.add(dateOrderLabel);
        panel1.add(dateOrderText);
        panel1.add(backButtonClient);
        panel1.add(createButtonClient);
        panel1.add(showMenuButton);
        panel1.add(generateBillButton);
        panel1.add(searchProductButton);
        panel1.add(chosenItemsLabel);
        panel1.add(chosenItemsText);
        frame1.setVisible(true);

    }

    /**
     * Show button action listener.
     *
     * @param actionListener the action listener
     */
    public void showButtonActionListener(final ActionListener actionListener)
    {
        showMenuButton.addActionListener(actionListener);
    }

    /**
     * Go back button action listener.
     *
     * @param actionListener the action listener
     */
    public void goBackButtonActionListener(final ActionListener actionListener)
    {
        backButtonClient.addActionListener(actionListener);
    }

    /**
     * Create order button action listener.
     *
     * @param actionListener the action listener
     */
    public void createOrderButtonActionListener(final ActionListener actionListener)
    {
        createButtonClient.addActionListener(actionListener);
    }

    /**
     * Generate bill button action listener.
     *
     * @param actionListener the action listener
     */
    public void generateBillButtonActionListener(final ActionListener actionListener)
    {
        generateBillButton.addActionListener(actionListener);
    }

    /**
     * Search button action listener.
     *
     * @param actionListener the action listener
     */
    public void searchButtonActionListener(final ActionListener actionListener)
    {
        searchProductButton.addActionListener(actionListener);
    }

    /**
     * Find order by id order.
     *
     * @param id the id
     * @return the order
     */
    public Order findOrderByID(int id) {
        Order myOrder = null;
        Iterator<Order> it = orderList.iterator();
        while (it.hasNext()) {
            Order curOrder = it.next();
            if (curOrder.getOrderId() == id)
                myOrder = curOrder;
        }
        return myOrder;
    }
}
