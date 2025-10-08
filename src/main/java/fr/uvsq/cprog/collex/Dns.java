package fr.uvsq.cprog.collex;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Dns {

    private List<DnsItem> items = new ArrayList<>();

    // Je garde le chemin du fichier afin de pouvoir le modifier plus tard
    private String filePath;

    public Dns() throws IOException {
        // Charger le fichier de propriétés
        Properties props = new Properties();
        props.load(new FileInputStream("config.properties"));
        filePath = props.getProperty("dns.file");

        // Lire toutes les lignes du fichier
        List<String> lignes = Files.readAllLines(Paths.get(filePath));

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

    // Méthode pour avoir une liste de DnsItem à partir d'un nom de domaine
    public List<DnsItem> getItems(String domaine) {
        List<DnsItem> result = new ArrayList<>();

        // Normaliser le domaine
        domaine = domaine.toLowerCase();

        for (DnsItem item : items) {
            String nomMachine = item.getNomMachine().getNom();

            // Vérifie si le nom correspond au domaine
            if (nomMachine.endsWith("." + domaine) || nomMachine.equals(domaine)) {
                result.add(item);
            }
        }

        return result;
    }

    // Méthode pour ajouter un DnsItem en mémoire et mettre à jour le fichier DNS
    // pour assurer la persistance
    public void addItem(NomMachine nom, AdresseIP ip) throws IOException {
        // Vérifier s'il existe déjà une machine ou IP dèjà utilisée
        for (DnsItem item : items) {
            if (item.getNomMachine().equals(nom)) {
                throw new IllegalArgumentException("Nom de machine déjà existant : " + nom);
            }
            if (item.getAdresseIP().equals(ip)) {
                throw new IllegalArgumentException("Adresse IP déjà utilisée : " + ip);
            }
        }

        // Ajouter à la liste en mémoire
        DnsItem newItem = new DnsItem(nom, ip);
        items.add(newItem);

        // Met à jour le fichier DNS en réécrivant l’ensemble du fichier avec le format
        // attendu "nomMachine adresseIP"
        List<String> lignes = new ArrayList<>();
        for (DnsItem item : items) {
            lignes.add(item.getNomMachine().toString() + " " + item.getAdresseIP().toString());
        }
        Files.write(Paths.get(filePath), lignes);
    }

}
