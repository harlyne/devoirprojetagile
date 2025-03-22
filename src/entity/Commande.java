package entity;

import java.util.ArrayList;
import java.util.List;

public class Commande {
    private int id;
    private int clientId;
    

    private String clientEmail;
    

    private List<ArticleCommande> articles;
    private double totalPrice;
    
    public Commande(int id, int clientId, String clientEmail) {
        this.id = id;
        this.clientId = clientId;
        this.clientEmail = clientEmail;
        this.articles = new ArrayList<>();
    }
    
    public int getId() { return id; }
    public int getClientId() {
        return clientId;
    }
    public String getClientEmail() {
        return clientEmail;
    }
    public List<ArticleCommande> getArticles() { return articles; }
    public double getTotalPrice() { return totalPrice; }
    
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    public void addItem(ArticleCommande article) {
        articles.add(article);
    }
}
