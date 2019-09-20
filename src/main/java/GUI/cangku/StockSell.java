package GUI.cangku;

import java.util.Date;

public class StockSell {
    private Integer sId;

    private Integer sNumber;

    private Integer sPrice;

    private Integer sStockId;

    private Date sUpdate;

    private String sRemarks;

    private String name;

    private Integer price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getsId() {
        return sId;
    }

    public void setsId(Integer sId) {
        this.sId = sId;
    }

    public Integer getsNumber() {
        return sNumber;
    }

    public void setsNumber(Integer sNumber) {
        this.sNumber = sNumber;
    }

    public Integer getsPrice() {
        return sPrice;
    }

    public void setsPrice(Integer sPrice) {
        this.sPrice = sPrice;
    }

    public Integer getsStockId() {
        return sStockId;
    }

    public void setsStockId(Integer sStockId) {
        this.sStockId = sStockId;
    }

    public Date getsUpdate() {
        return sUpdate;
    }

    public void setsUpdate(Date sUpdate) {
        this.sUpdate = sUpdate;
    }

    public String getsRemarks() {
        return sRemarks;
    }

    public void setsRemarks(String sRemarks) {
        this.sRemarks = sRemarks;
    }
}