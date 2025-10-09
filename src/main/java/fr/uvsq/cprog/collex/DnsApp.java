package fr.uvsq.cprog.collex;

import java.io.IOException;

public class DnsApp {

    private Dns dns;
    private DnsTUI tui;

    public DnsApp() throws IOException {
        dns = new Dns(); // Initialise la base DNS à partir du fichier
        tui = new DnsTUI(dns); // Interface utilisateur textuelle
    }

    // Boucle principale de l'application, elle récupère les commandes utilisateur
    // et les exécute jusqu'à la sortie
    public void run() {
        System.out.println("=== Application DNS Interactive ===");
        while (true) {
            Commande commande = tui.nextCommand(); // Récupère la prochaine commande utilisateur
            if (commande != null) {
                commande.execute(); // Exécute la commande
            } else {
                System.out.println("Commande invalide !");
            }
        }
    }
}
