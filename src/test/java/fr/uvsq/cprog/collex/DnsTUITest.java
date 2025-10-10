package fr.uvsq.cprog.collex;

import org.junit.*;
import static org.junit.Assert.*;
import java.io.*;

public class DnsTUITest {

    private Dns dns;

    @Before
    public void setUp() throws IOException {
        // Création d'un DNS minimal pour les tests
        dns = new DnsMock(); // On peut créer une implémentation mockée de Dns
    }

    @Test
    public void testCommandeAdd() {
        String input = "add 192.168.0.1 host.domain\n";
        DnsTUI tui = new DnsTUI(dns, new Scanner(new ByteArrayInputStream(input.getBytes())));
        Commande cmd = tui.nextCommande();
        assertTrue(cmd instanceof CommandeAjoutItem);
    }

    @Test
    public void testCommandeLs() {
        String input = "ls domain\n";
        DnsTUI tui = new DnsTUI(dns, new Scanner(new ByteArrayInputStream(input.getBytes())));
        Commande cmd = tui.nextCommande();
        assertTrue(cmd instanceof CommandeRechercheDomaine);
    }

    @Test
    public void testCommandeLsAvecOptionA() {
        String input = "ls -a domain\n";
        DnsTUI tui = new DnsTUI(dns, new Scanner(new ByteArrayInputStream(input.getBytes())));
        Commande cmd = tui.nextCommande();
        assertTrue(cmd instanceof CommandeRechercheDomaine);
    }

    @Test
    public void testCommandeRechercheIP() {
        String input = "192.168.0.1\n";
        DnsTUI tui = new DnsTUI(dns, new Scanner(new ByteArrayInputStream(input.getBytes())));
        Commande cmd = tui.nextCommande();
        assertTrue(cmd instanceof CommandeRechercheIP);
    }

    @Test
    public void testCommandeRechercheNom() {
        String input = "host.domain\n";
        DnsTUI tui = new DnsTUI(dns, new Scanner(new ByteArrayInputStream(input.getBytes())));
        Commande cmd = tui.nextCommande();
        assertTrue(cmd instanceof CommandeRechercheNom);
    }

    @Test
    public void testCommandeQuit() {
        String input = "exit\n";
        DnsTUI tui = new DnsTUI(dns, new Scanner(new ByteArrayInputStream(input.getBytes())));
        Commande cmd = tui.nextCommande();
        assertTrue(cmd instanceof CommandeQuitter);
    }

    @Test
    public void testCommandeInvalide() {
        String input = "invalid command\n";
        DnsTUI tui = new DnsTUI(dns, new Scanner(new ByteArrayInputStream(input.getBytes())));
        Commande cmd = tui.nextCommande();
        assertNull(cmd);
    }
}
