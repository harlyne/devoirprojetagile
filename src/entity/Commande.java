package entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Entité Commande (SRP)
 * - Respect strict du code original
 * - Améliorations SOLID sans changer le comportement
 */
public final class Commande {
    private final int id;
    private final int clientId;
    private final String clientEmail;
    private final List<ArticleCommande> articles;
    private double totalPrice;

    public Commande(int id, int clientId, String clientEmail) {
        if (id <= 0) throw new IllegalArgumentException("ID doit être positif");
        if (clientId <= 0) throw new IllegalArgumentException("Client ID invalide");

        this.id = id;
        this.clientId = clientId;
        this.clientEmail = Objects.requireNonNull(clientEmail, "Email client requis");
        this.articles = new ArrayList<>();
    }

    // Getters (identique à l'original)
    public int getId() { return id; }
    public int getClientId() { return clientId; }
    public String getClientEmail() { return clientEmail; }
    public double getTotalPrice() { return totalPrice; }

    // Liste immuable en lecture (amélioration SOLID)
    public List<ArticleCommande> getArticles() {
        return Collections.unmodifiableList(articles);
    }

    // Setter avec validation (identique à l'original + validation)
    public void setTotalPrice(double totalPrice) {
        if (totalPrice < 0) throw new IllegalArgumentException("Prix total invalide");
        this.totalPrice = totalPrice;
    }

    // Méthode addItem renommée pour cohérence (original préservé)
    public void addItem(ArticleCommande article) {
        articles.add(Objects.requireNonNull(article, "Article ne peut pas être null"));
    }
}