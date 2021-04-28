package model;


import exception.QuantityException;
import exception.PriceException;
import exception.IdArticleException;

public class Listing {
    private Integer quantity;
    private Double price;
    private Integer article;

    public Listing(Integer quantity, Double price, Integer article) throws QuantityException, PriceException, IdArticleException {
        setQuantity(quantity);
        setPrice(price);
        setArticle(article);
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
