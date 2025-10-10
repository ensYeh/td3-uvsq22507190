package fr.uvsq.cprog.collex;

import org.junit.Test;
import static org.mockito.Mockito.*;

public class CommandeRechercheNomTest {

    @Test
    public void testRechercheNomValideTrouve() {
        Dns dnsMock = mock(Dns.class);
        NomMachine machine = new NomMachine("host.domain");
        AdresseIP ip = new AdresseIP("192.168.0.1");
        DnsItem item = new DnsItem(machine, ip);

        when(dnsMock.getItem(machine)).thenReturn(item);

        CommandeRechercheNom cmd = new CommandeRechercheNom(dnsMock, "host.domain");
        cmd.execute();

        // Vérifie que getItem a été appelé
        verify(dnsMock, times(1)).getItem(machine);
    }

    @Test
    public void testRechercheNomValideIntrouvable() {
        Dns dnsMock = mock(Dns.class);
        NomMachine machine = new NomMachine("host.domain");

        when(dnsMock.getItem(machine)).thenReturn(null);

        CommandeRechercheNom cmd = new CommandeRechercheNom(dnsMock, "host.domain");
        cmd.execute();

        verify(dnsMock, times(1)).getItem(machine);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRechercheNomInvalide() {
        Dns dnsMock = mock(Dns.class);

        // Nom invalide déclenche IllegalArgumentException
        CommandeRechercheNom cmd = new CommandeRechercheNom(dnsMock, "host..domain");
        cmd.execute();
    }
}
