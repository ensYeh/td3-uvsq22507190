package fr.uvsq.cprog.collex;

public class CommandeRechercheIP implements Commande {
    private Dns dns;
    private String ip;

    public CommandeRechercheIP(Dns dns, String ip) {
        this.dns = dns;
        this.ip = ip;
    }

    @Override
    public void execute() {
        AdresseIP adresse = new AdresseIP(ip);
        DnsItem item = dns.getItem(adresse);
        if (item != null) {
            System.out.println(item.getNomMachine());
        } else {
            System.out.println("ERREUR : Adresse IP introuvable !");
        }
    }
}
