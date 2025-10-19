package fr.uvsq.cprog.collex;

import java.util.Scanner;

public class DnsTUI {

    private Dns dns;
    private Scanner scanner;

    public DnsTUI(Dns dns) {
        this.dns = dns;
        this.scanner = new Scanner(System.in);
    }

    // Constructeur pour les tests unitaires
    public DnsTUI(Dns dns, Scanner scanner) {
        this.dns = dns;
        this.scanner = scanner;
    }

    // Lit une ligne saisie par l'utilisateur et retourne la commande correspondante
    public Commande nextCommande() {
        System.out.print("> ");
        String ligne = scanner.nextLine().trim();
        if (ligne.isEmpty())
            return null;
        return parseCommande(ligne);
    }

    private Commande parseCommande(String ligne) {
        String[] tokens = ligne.split("\\s+");

        // Commande add ip nom
        if (tokens[0].equalsIgnoreCase("add") && tokens.length == 3) {
            return new CommandeAjoutItem(dns, tokens[1], tokens[2]);
        }

        // Commande ls [-a] domaine
        if (tokens[0].equalsIgnoreCase("ls")) {
            boolean triParIP = false;
            String domaine;
            if (tokens.length == 3 && tokens[1].equalsIgnoreCase("-a")) {
                triParIP = true;
                domaine = tokens[2];
            } else if (tokens.length == 2) {
                domaine = tokens[1];
            } else {
                return null;
            }
            return new CommandeRechercheDomaine(dns, domaine, triParIP);
        }

        // Si c’est une IP ou un nom
        if (tokens.length == 1) {
            String input = tokens[0];
            if (input.matches("\\d+\\.\\d+\\.\\d+\\.\\d+")) {
                return new CommandeRechercheIP(dns, input);
            } else if (input.equalsIgnoreCase("exit") || input.equalsIgnoreCase("quit")) {
                return new CommandeQuitter();
            } else {
                return new CommandeRechercheNom(dns, input);
            }
        }
        return null;
    }
}
