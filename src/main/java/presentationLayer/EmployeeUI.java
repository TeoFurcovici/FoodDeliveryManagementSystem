package presentationLayer;

import businessLayer.DeliveryService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;
import java.awt.*;

/**
 * The type Employee ui.
 */
public class EmployeeUI extends JFrame implements Observer {

    private DeliveryService deliveryService;
    private JButton backButtonEmployee=new JButton("Go Back!");

    /**
     * Instantiates a new Employee ui.
     *
     * @param deliveryService the delivery service
     */
    public EmployeeUI(DeliveryService deliveryService) {
        this.deliveryService=deliveryService;
        JFrame frameEmployee = new JFrame();
        frameEmployee.setSize(550,500);
        frameEmployee.setVisible(true);
        frameEmployee.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        JPanel panelEmployee=new JPanel();
        panelEmployee.setBackground(Color.decode("#ccd9ff"));
        panelEmployee.setLayout(null);
        frameEmployee.add(panelEmployee);
        backButtonEmployee.setBounds(355,7,165,25);

        panelEmployee.add(backButtonEmployee);
        backButtonEmployeeActionListenerProduct(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameEmployee.setVisible(false); }
        });
    }

    /**
     * This function is used when the notifyObservers is called
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        int x = JOptionPane.showConfirmDialog(null, arg, "Notification !", 2);
        if (x == 0) {
            JOptionPane.showMessageDialog(null,"Employee will take your order!");
            this.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(null,"Employee is busy now , but will still help you  later!");
            this.setVisible(false);
        }
    }

    /**
     * Back button employee action listener product.
     *
     * @param actionListener the action listener
     */
    public void backButtonEmployeeActionListenerProduct(final ActionListener actionListener)
    {
        backButtonEmployee.addActionListener(actionListener);
    }
}
