package exception;

public class IdArticleException extends Throwable {
    private Integer wrongIdArticle;

    public IdArticleException(Integer wrongIdArticle){
        this.wrongIdArticle = wrongIdArticle;
    }
    public IdArticleException(){
        this(-118218);
    }
    public String getMessage(){
        return "La valeur " + wrongIdArticle + " contient autre chose qu'une valeur numérique";
    }
}

