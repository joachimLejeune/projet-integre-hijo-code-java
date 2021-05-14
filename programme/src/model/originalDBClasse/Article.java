package model.originalDBClasse;

import java.util.ArrayList;
import exception.IdArticleException;
import exception.PriceException;
import exception.VATException;
import exception.NumAisleException;


public class Article {
    private Integer idArticle;
    private String wording;
    private Double price;
    private Double VAT;
    private Integer aisle;

    public Article(Integer idArticle, String wording, Double price, Double VAT, Integer aisle) throws IdArticleException, PriceException, VATException, NumAisleException {
        setIdArticle(idArticle);
        this.wording = wording;
        setPrice(price);
        setVAT(VAT);
        setAisle(aisle);
    }

    public String getWording(){
        return wording;
    }
    public void setIdArticle(Integer idArticle) throws IdArticleException {
        if (idArticle instanceof Integer) {
            this.idArticle = idArticle;
        } else {
            throw new IdArticleException(idArticle);
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
    public void setVAT(Double VAT) throws VATException {
        ArrayList<Double> arrayList = new ArrayList<Double>();
        arrayList.add(0.21);
        arrayList.add(0.06);
        arrayList.add(0.00);

        if(arrayList.contains(VAT)){
            this.VAT = VAT;
        }
        else{
            throw new VATException(VAT);
        }
    }

    public void setAisle(Integer aisle) throws NumAisleException {
        if (aisle instanceof Integer) {
            this.aisle = aisle;
        } else {
            throw new NumAisleException(aisle);
        }
    }
    public Double getPrice() {
        return price;
    }
    public Double getVAT() {
        return VAT;
    }

    public Integer getIdArticle() {
        return idArticle;
    }
}
