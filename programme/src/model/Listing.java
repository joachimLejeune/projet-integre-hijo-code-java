package model;


import exception.IdBillException;
import exception.QuantityException;
import exception.PriceException;
import exception.IdArticleException;

public class Listing {
    private Integer quantity;
    private Double price;
    private Integer article;
    private Integer idBill;

    public Listing(Integer quantity, Double price, Integer article, Integer idBill) throws QuantityException, PriceException, IdArticleException, IdBillException {
        setQuantity(quantity);
        setPrice(price);
        setArticle(article);
        setIdBill(idBill);
    }

    public void setIdBill(Integer idBill) throws IdBillException {
        if (idBill instanceof Integer) {
            this.idBill = idBill;
        } else {
            throw new IdBillException(idBill);
        }
    }
    public void setQuantity(Integer quantity) throws QuantityException {
        if((quantity >= 0) && (quantity instanceof Integer)){
            this.quantity = quantity;
        }
        else{
            throw new QuantityException(quantity);
        }
    }

    public void setPrice(Double price) throws PriceException {
        if(price <= 0){
            throw new PriceException(price);
        }
        else{
            this.price = price;
        }
    }

    public void setArticle(Integer article) throws IdArticleException {
        if (article instanceof Integer) {
            this.article = article;
        } else {
            throw new IdArticleException(article);
        }
    }
}
