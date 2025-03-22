package entity;

public class ArticleCommande {
    private Article article;
    private int quantity;
    
    public ArticleCommande(Article article, int quantity) {
        this.article = article;
        this.quantity = quantity;
    }
    
    public Article getArticle() { return article; }
    public int getQuantity() { return quantity; } 
}
