package fr.uvsq.cprog.collex;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Dns {

    private List<DnsItem> items = new ArrayList<>();

    public Dns() throws IOException {
        // Charger le fichier de propriétés
        Properties props = new Properties();
        props.load(new FileInputStream("config.properties"));
        String path = props.getProperty("dns.file");

        // Lire toutes les lignes du fichier
        List<String> lignes = Files.readAllLines(Paths.get(path));

        // Créer les DnsItem et les ajouter à la liste
        for (String ligne : lignes) {
            String[] parts = ligne.split("\\s+");
            if (parts.length == 2) {
                NomMachine nom = new NomMachine(parts[0]);
                AdresseIP ip = new AdresseIP(parts[1]);
                items.add(new DnsItem(nom, ip));
            } else {
                System.err.println("Ligne invalide dans le fichier DNS : " + ligne);
            }
        }
    }

    // Méthode pour chercher par NomMachine
    public DnsItem getItem(NomMachine nom) {
        for (DnsItem item : items) {
            if (item.getNom().equals(nom)) {
                return item;
            }
        }
        return null;
    }

    // Méthode pour chercher par AdresseIP
    public DnsItem getItem(AdresseIP ip) {
        for (DnsItem item : items) {
            if (item.getIp().equals(ip)) {
                return item;
            }
        }
        return null;
    }

}
