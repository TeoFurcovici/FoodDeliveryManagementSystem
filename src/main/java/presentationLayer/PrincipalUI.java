package presentationLayer;

import businessLayer.DeliveryService;
import presentationLayer.AdminUI;
import presentationLayer.ClientUI;
import presentationLayer.EmployeeUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The type Principal ui.
 */
public class PrincipalUI {

    private AdminUI adminUI;
    private ClientUI clientUI;
    private EmployeeUI employeeUI;
    private JButton adminButton=new JButton("Admin");
    private JButton clientButton=new JButton("Client");
    private JButton employeeButton=new JButton("Employee");
    private JButton registerButton=new JButton("Register");
    private JButton logInButton=new JButton("Log in");
    private JLabel usernameLabel=new JLabel("Username:");
    private JLabel passLabel=new JLabel("Password:");
    private JTextField usernameText=new JTextField();
    private JPasswordField passText=new JPasswordField();
    /**
     * The Delivery service.
     */
    DeliveryService deliveryService;

    /**
     * Gets username text.
     *
     * @return the username text
     */
    public String getUsernameText() {
        return usernameText.getText();
    }

    /**
     * Gets pass text.
     *
     * @return the pass text
     */
    public String getPassText() {
        return passText.getText();
    }

    /**
     * Instantiates a new Principal ui.
     *
     * @param deliveryService the delivery service
     */
    public PrincipalUI(DeliveryService deliveryService)
    {
        this.deliveryService=deliveryService;
        JFrame framePrincipal = new JFrame();
        framePrincipal.setSize(550, 250);
        framePrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         JPanel principalPanel = new JPanel();
        principalPanel.setBackground(Color.decode("#ccd9ff"));
        principalPanel.setLayout(null);
        framePrincipal.add(principalPanel);
        JFrame logInFrame=new JFrame();
        logInFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        logInFrame.setSize(300,250);
        JPanel logInPanel=new JPanel();
        logInPanel.setBackground(Color.decode("#ccd9ff"));
        logInPanel.setLayout(null);
        logInFrame.add(logInPanel);

        adminButton.setBounds(20,100,140,30);
        clientButton.setBounds(200,100,140,30);
        employeeButton.setBounds(380,100,140,30);
        registerButton.setBounds(67,127,140,30);
        logInButton.setBounds(67,167,140,30);

        usernameLabel.setBounds(7,47,120,25);
        usernameText.setBounds(100,47,120,25);
        passLabel.setBounds(7,87,120,25);
        passText.setBounds(100,87,120,25);

        principalPanel.add(adminButton);
        principalPanel.add(clientButton);
        principalPanel.add(employeeButton);
        logInPanel.add(usernameLabel);
        logInPanel.add(usernameText);
        logInPanel.add(passLabel);
        logInPanel.add(passText);
        logInPanel.add(registerButton);
        logInPanel.add(logInButton);
        adminButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               adminUI=new AdminUI(deliveryService);
            }
        });
        clientButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logInFrame.setVisible(true);
            }
        });
        registerButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeliveryService deliveryService=new DeliveryService();
                deliveryService.addToClientsList(usernameText.getText(),passText.getText());
                JOptionPane.showMessageDialog(null,"Successfully registered!");
            }
        });
        logInButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientUI=new ClientUI(deliveryService);

            }
        });
        employeeButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                employeeUI=new EmployeeUI(deliveryService);
            }
        });
        framePrincipal.setVisible(true);
    }

    /**
     * Admin button action listener.
     *
     * @param actionListener the action listener
     */
    public void adminButtonActionListener(final ActionListener actionListener)
    {
        adminButton.addActionListener(actionListener);
    }

    /**
     * Client button action listener.
     *
     * @param actionListener the action listener
     */
    public void clientButtonActionListener(final ActionListener actionListener)
    {
        clientButton.addActionListener(actionListener);
    }

    /**
     * Employee button action listener.
     *
     * @param actionListener the action listener
     */
    public void employeeButtonActionListener(final ActionListener actionListener)
    {
        employeeButton.addActionListener(actionListener);
    }

    /**
     * Register button action listener.
     *
     * @param actionListener the action listener
     */
    public void registerButtonActionListener(final ActionListener actionListener)
    {
        registerButton.addActionListener(actionListener);
    }

    /**
     * Log in button action listener.
     *
     * @param actionListener the action listener
     */
    public void logInButtonActionListener(final ActionListener actionListener)
    {
        logInButton.addActionListener(actionListener);
    }
}
