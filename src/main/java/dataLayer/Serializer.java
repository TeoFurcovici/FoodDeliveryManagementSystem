package dataLayer;
import businessLayer.DeliveryService;

import java.io.*;

/**
 * The type Serializer.
 */
public class Serializer {

    /**
     * Serialize.
     *
     * @param deliveryServiceSer the delivery service ser
     * @throws IOException the io exception
     */
    public static void serialize(DeliveryService deliveryServiceSer) throws IOException { //writeItems
        FileOutputStream file=new FileOutputStream("incercare.txt");
        ObjectOutputStream out=new ObjectOutputStream(file);
        out.writeObject(deliveryServiceSer);
        System.out.println("[IN SERIALIZARE]"+deliveryServiceSer.getMenuItems());
        System.out.println("[IN SERIALIZARE ORDERS]"+deliveryServiceSer.getOrders());
        out.close();
        file.close();
    }

    public static DeliveryService deserialize()  {//readItems
        DeliveryService deliveryService=new DeliveryService();
        try {
            FileInputStream fileInputStream = new FileInputStream("incercare.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            deliveryService = (DeliveryService) objectInputStream.readObject();
            System.out.println("[IN DESERIALIZARE MENU ITEMS]"+deliveryService.getMenuItems());
            System.out.println("[IN DESERIALIZARE ORDERS]"+deliveryService.getOrders());
            objectInputStream.close();
            fileInputStream.close();
        }
        catch (Exception e)
        {
           e.printStackTrace();
        }
        return deliveryService;
    }

}
