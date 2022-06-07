package cat.uvic.teknos.m06.gamestore.domain.models;

import javax.persistence.*;

@Entity
public class Shipment {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int ShipmentID;
    private String company;
    @Transient
    private int OrderId;

    public int getShipmentID() {
        return ShipmentID;
    }

    public void setShipmentID(int shipmentID) {
        ShipmentID = shipmentID;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getOrderId() {
        return OrderId;
    }

    public void setOrderId(int orderId) {
        OrderId = orderId;
    }
}
