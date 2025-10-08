package fr.uvsq.cprog.collex;

import java.util.Scanner;

public class DnsTUI {

    private Dns dns;
    private Scanner scanner;

    public DnsTUI(Dns dns) {
        this.dns = dns;
        this.scanner = new Scanner(System.in);
    }
}
