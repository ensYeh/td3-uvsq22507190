package fr.uvsq.cprog.collex;

import java.util.*;

public class CommandeRechercheDomaine implements Commande {
    private Dns dns;
    private String domaine;
    private boolean triParIP;

    public CommandeRechercheDomaine(Dns dns, String domaine, boolean triParIP) {
        this.dns = dns;
        this.domaine = domaine;
        this.triParIP = triParIP;
    }

    @Override
    public void execute() {
        // Récupérer la liste des DnsItem correspondant au domaine
        List<DnsItem> items = dns.getItems(domaine);

        if (triParIP) {
            // Le Comparator généré par Comparator.comparing appelle en interne compare(a,b)
            // qui utilise automatiquement la méthode compareTo déjà implémentée dans
            // AdresseIP
            items.sort(Comparator.comparing(DnsItem::getAdresseIP));
        } else {
            // Tri par nom de machine
            items.sort(Comparator.comparing(DnsItem::getNomMachine));
        }

        // Affichage
        for (DnsItem item : items) {
            System.out.println(item);
        }
    }

}
