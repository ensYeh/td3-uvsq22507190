package fr.uvsq.cprog.collex;

import java.io.IOException;

public class DnsApp {

    private Dns dns;
    private DnsTUI tui;

    public DnsApp() throws IOException {
        dns = new Dns(); // Initialise la base DNS à partir du fichier
        tui = new DnsTUI(dns); // Interface utilisateur textuelle
    }
}
