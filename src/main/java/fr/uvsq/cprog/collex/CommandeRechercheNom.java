package fr.uvsq.cprog.collex;

public class CommandeRechercheNom implements Commande {
    private Dns dns;
    private String nom;

    public CommandeRechercheNom(Dns dns, String nom) {
        this.dns = dns;
        this.nom = nom;
    }

    @Override
    public void execute() {
        NomMachine machine = new NomMachine(nom);
        DnsItem item = dns.getItem(machine);
        if (item != null) {
            System.out.println(item.getAdresseIP());
        } else {
            System.out.println("ERREUR : Nom de machine introuvable !");
        }
    }
}
