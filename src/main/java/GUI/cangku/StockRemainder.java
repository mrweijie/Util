package GUI.cangku;

import java.util.Date;

public class StockRemainder {
    private Integer srId;

    private Integer srNumber;

    private Integer srStockId;

    private Integer price;

    private String name;

    private Date srUpdate;

    private String srRemarks;

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSrId() {
        return srId;
    }

    public void setSrId(Integer srId) {
        this.srId = srId;
    }

    public Integer getSrNumber() {
        return srNumber;
    }

    public void setSrNumber(Integer srNumber) {
        this.srNumber = srNumber;
    }

    public Integer getSrStockId() {
        return srStockId;
    }

    public void setSrStockId(Integer srStockId) {
        this.srStockId = srStockId;
    }

    public Date getSrUpdate() {
        return srUpdate;
    }

    public void setSrUpdate(Date srUpdate) {
        this.srUpdate = srUpdate;
    }

    public String getSrRemarks() {
        return srRemarks;
    }

    public void setSrRemarks(String srRemarks) {
        this.srRemarks = srRemarks;
    }
}