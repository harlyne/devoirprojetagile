package entity;

public final class ArticleCommande {
    private final Article article;
    private final int quantity;
    
    public ArticleCommande(Article article, int quantity) {
        if (article == null) throw new IllegalArgumentException("Article requis");
        if (quantity <= 0) throw new IllegalArgumentException("Quantité invalide");
        this.article = article;
        this.quantity = quantity;
    }
    
    public Article getArticle() { return article; }
    public int getQuantity() { return quantity; } 
}
