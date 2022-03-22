package businessLayer;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * The type Order.
 */
public class Order implements Serializable {
    private int OrderId;
    private int clientId;
    private int day;
    private int month;
    private int year;

    /**
     * Gets day.
     *
     * @return the day
     */
    public int getDay() {
        return day;
    }

    /**
     * Sets day.
     *
     * @param day the day
     */
    public void setDay(int day) {
        this.day = day;
    }

    /**
     * Gets month.
     *
     * @return the month
     */
    public int getMonth() {
        return month;
    }

    /**
     * Sets month.
     *
     * @param month the month
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * Gets year.
     *
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * Sets year.
     *
     * @param year the year
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Gets order id.
     *
     * @return the order id
     */
    public int getOrderId() {
        return OrderId;
    }

    /**
     * Sets order id.
     *
     * @param orderId the order id
     */
    public void setOrderId(int orderId) {
        OrderId = orderId;
    }

    /**
     * Gets client id.
     *
     * @return the client id
     */
    public int getClientId() {
        return clientId;
    }

    /**
     * Sets client id.
     *
     * @param clientId the client id
     */
    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    /**
     * Gets hours.
     *
     * @return the hours
     */
    public int getHours()
  {
      Date time=new Date();
     return time.getHours();
  }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return OrderId == order.OrderId &&
                clientId == order.clientId &&
                day == order.day &&
                month == order.month &&
                year == order.year;
    }

    @Override
    public int hashCode() {
        return Objects.hash(OrderId, clientId, day, month, year);
    }

    @Override
    public String toString() {
        return "Order id: "+getOrderId()+"\n"
                +"Client id: "+getClientId()+"\n"+
                "Order Date: "+getDay()+"/"+getMonth()+"/"+getYear()+"\n" +
                "Order Time:"+getHours()+"\n\n\n";
    }
}
