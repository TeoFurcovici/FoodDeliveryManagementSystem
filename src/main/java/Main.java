import businessLayer.DeliveryService;
import dataLayer.Serializer;
import presentationLayer.AdminUI;
import presentationLayer.PrincipalUI;

import java.io.*;


public class Main {

    public static void main(String[] args) {
       // businessLayer.DeliveryService deliveryService=new businessLayer.DeliveryService();
       // presentationLayer.EmployeeUI adminUI=new presentationLayer.EmployeeUI();
        DeliveryService deliveryService=new DeliveryService();
        deliveryService= Serializer.deserialize();
         PrincipalUI principalUI=new PrincipalUI(deliveryService);
        //AdminUI adminUI=new AdminUI(deliveryService);
       // presentationLayer.AdminUI adminUI=new presentationLayer.AdminUI(deliveryService);
        //presentationLayer.ClientUI clientUI= new presentationLayer.ClientUI(deliveryService);

    }
}