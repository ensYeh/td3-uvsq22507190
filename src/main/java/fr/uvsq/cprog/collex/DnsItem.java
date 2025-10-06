package fr.uvsq.cprog.collex;

public class DnsItem {
    private NomMachine nomMachine;
    private AdresseIP adresseIP;

    public DnsItem(NomMachine nomMachine, AdresseIP adresseIP) {
        this.nomMachine = nomMachine;
        this.adresseIP = adresseIP;
    }

    public NomMachine getNomMachine() {
        return nomMachine;
    }

    public AdresseIP getAdresseIP() {
        return adresseIP;
    }

    @Override
    public String toString() {
        return adresseIP + " " + nomMachine;
    }
}
