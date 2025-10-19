package fr.uvsq.cprog.collex;

import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.io.ByteArrayInputStream;
import java.util.Scanner;

public class DnsTUITest {

    private Dns dnsMock;

    @Before
    public void setUp() {
        // Création d'un mock de Dns
        dnsMock = mock(Dns.class);
    }

    @Test
    public void testCommandeAdd() {
        String input = "add 192.168.0.1 host.domain\n";
        DnsTUI tui = new DnsTUI(dnsMock, new Scanner(new ByteArrayInputStream(input.getBytes())));
        Commande cmd = tui.nextCommande();
        assertTrue(cmd instanceof CommandeAjoutItem);
    }

    @Test
    public void testCommandeLs() {
        String input = "ls domain\n";
        DnsTUI tui = new DnsTUI(dnsMock, new Scanner(new ByteArrayInputStream(input.getBytes())));
        Commande cmd = tui.nextCommande();
        assertTrue(cmd instanceof CommandeRechercheDomaine);
    }

    @Test
    public void testCommandeLsAvecOptionA() {
        String input = "ls -a domain\n";
        DnsTUI tui = new DnsTUI(dnsMock, new Scanner(new ByteArrayInputStream(input.getBytes())));
        Commande cmd = tui.nextCommande();
        assertTrue(cmd instanceof CommandeRechercheDomaine);
    }

    @Test
    public void testCommandeRechercheIP() {
        String input = "192.168.0.1\n";
        DnsTUI tui = new DnsTUI(dnsMock, new Scanner(new ByteArrayInputStream(input.getBytes())));
        Commande cmd = tui.nextCommande();
        assertTrue(cmd instanceof CommandeRechercheIP);
    }

    @Test
    public void testCommandeRechercheNom() {
        String input = "host.domain\n";
        DnsTUI tui = new DnsTUI(dnsMock, new Scanner(new ByteArrayInputStream(input.getBytes())));
        Commande cmd = tui.nextCommande();
        assertTrue(cmd instanceof CommandeRechercheNom);
    }

    @Test
    public void testCommandeQuit() {
        String input = "exit\n";
        DnsTUI tui = new DnsTUI(dnsMock, new Scanner(new ByteArrayInputStream(input.getBytes())));
        Commande cmd = tui.nextCommande();
        assertTrue(cmd instanceof CommandeQuitter);
    }

    @Test
    public void testCommandeInvalide() {
        String input = "invalid command\n";
        DnsTUI tui = new DnsTUI(dnsMock, new Scanner(new ByteArrayInputStream(input.getBytes())));
        Commande cmd = tui.nextCommande();
        assertNull(cmd);
    }
}
