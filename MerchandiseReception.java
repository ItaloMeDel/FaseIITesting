package ModuloCompras;

import java.sql.Date;

public class MerchandiseReception {
    private int id;
    private int purchaseOrderId;
    private Date receptionDate;
    private String observations;

    public MerchandiseReception(int id, int purchaseOrderId, Date receptionDate, String observations) {
        this.id = id;
        this.purchaseOrderId = purchaseOrderId;
        this.receptionDate = receptionDate;
        this.observations = observations;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public void setPurchaseOrderId(int purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

    public Date getReceptionDate() {
        return receptionDate;
    }

    public void setReceptionDate(Date receptionDate) {
        this.receptionDate = receptionDate;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }
}