package model.tableModelTool;

public class RowListing {
    private String wording;
    private Integer quantity;
    private Double unitPriceWVAT;
    private Double totalPriceWVAT;
    private Double VAT;
    private Double totalPrice;

    public RowListing(String wording, Integer quantity, Double unitPriceWVAT, Double totalPriceWVAT, Double VAT, Double totalPrice){
        this.wording = wording;
        this.quantity = quantity;
        this.unitPriceWVAT = unitPriceWVAT;
        this.totalPriceWVAT = totalPriceWVAT;
        this.VAT = VAT;
        this.totalPrice = totalPrice;
    }

    // getter
    public String getWording() {
        return wording;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public Double getUnitPriceWVAT() {
        return unitPriceWVAT;
    }
    public Double getTotalPriceWVAT() {
        return totalPriceWVAT;
    }
    public Double getVAT() {
        return VAT;
    }
    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setQuantity(Integer aValue) {
        this.quantity = quantity;
    }

    public void setUnitPriceWVAT(Double aValue) {
        this.unitPriceWVAT = unitPriceWVAT;
    }
}
