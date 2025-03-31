package config;

import database.Database;
import database.MySQLDatabase;
import services.impl.CommandeService;
import services.impl.SMTPEmailService;
import services.impl.TaxablePricingCalculator;
import services.interfaces.EmailService;
import services.interfaces.OrderProcessor;
import services.interfaces.PricingCalculator;

public final class AppConfig {
    private static Database database;
    private static EmailService emailService;
    private static PricingCalculator pricingCalculator;
    private static OrderProcessor orderProcessor;

    private AppConfig() {}

    public static synchronized Database getDatabase() {
        if (database == null) {
            database = new MySQLDatabase();
        }
        return database;
    }

    public static synchronized EmailService getEmailService() {
        if (emailService == null) {
            emailService = new SMTPEmailService();
        }
        return emailService;
    }

    public static synchronized PricingCalculator getPricingCalculator() {
        if (pricingCalculator == null) {
            pricingCalculator = new TaxablePricingCalculator();
        }
        return pricingCalculator;
    }

    public static synchronized OrderProcessor getOrderProcessor() {
        if (orderProcessor == null) {
            orderProcessor = createCommandeService();
        }
        return orderProcessor;
    }

    private static CommandeService createCommandeService() {
        return new CommandeService(
                getDatabase(),
                getEmailService(),
                getPricingCalculator()
        );
    }

    static void reset() {
        database = null;
        emailService = null;
        pricingCalculator = null;
        orderProcessor = null;
    }
}