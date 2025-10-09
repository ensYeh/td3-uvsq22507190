package fr.uvsq.cprog.collex;

public class CommandeAjoutItem implements Commande {
    private Dns dns;
    private String ip;
    private String nom;

    public CommandeAjoutItem(Dns dns, String ip, String nom) {
        this.dns = dns;
        this.ip = ip;
        this.nom = nom;
    }

    @Override
    public void execute() {
        try {
            dns.addItem(new NomMachine(nom), new AdresseIP(ip));
            System.out.println("Item ajouté avec succès !");
        } catch (IllegalArgumentException e) {
            System.out.println("ERREUR : " + e.getMessage());
        } catch (java.io.IOException e) {
            System.out.println("ERREUR : Impossible d’écrire dans le fichier DNS (" + e.getMessage() + ")");
        }
    }
}
