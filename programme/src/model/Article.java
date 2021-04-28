package model;

public class Article {
    private Integer idArticle;
    private String wording;
    private Double price;
    private Double VAT;
    private Integer numAisle;

    public Article(Integer idArticle,String wording, Double price,Double VAT, Integer numAisle){
        this.idArticle = idArticle;
        this.wording = wording;
        this.price = price;
        this.VAT = VAT;
        this.numAisle = numAisle;
    }
}
