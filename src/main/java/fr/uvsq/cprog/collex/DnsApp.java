package fr.uvsq.cprog.collex;

import java.io.IOException;

public class DnsApp {

    private Dns dns;
    private DnsTUI tui;

    public DnsApp() throws IOException {
        dns = new Dns("config.properties"); // Initialise la base DNS à partir du fichier
        tui = new DnsTUI(dns); // Interface utilisateur textuelle
    }

    // Boucle principale de l'application, elle récupère les commandes utilisateur
    // et les exécute jusqu'à la sortie
    public void run() {
        System.out.println("=== Application DNS Interactive ===");
        while (true) {
            Commande commande = tui.nextCommande(); // Récupère la prochaine commande utilisateur
            if (commande != null) {
                commande.execute(); // Exécute la commande
            } else {
                System.out.println("Commande invalide !");
            }
        }
    }

    public static void main(String[] args) {
        try {
            DnsApp app = new DnsApp();
            app.run();
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement du DNS : " + e.getMessage());
        }
    }
}
