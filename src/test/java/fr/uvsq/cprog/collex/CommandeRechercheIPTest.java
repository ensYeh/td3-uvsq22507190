package fr.uvsq.cprog.collex;

import org.junit.Test;
import static org.mockito.Mockito.*;
import java.io.IOException;

public class CommandeRechercheIPTest {

    @Test
    public void testRechercheIPValideTrouvee() {
        Dns dnsMock = mock(Dns.class);
        AdresseIP adresse = new AdresseIP("192.168.0.1");
        DnsItem item = new DnsItem(new NomMachine("host.domain"), adresse);

        when(dnsMock.getItem(adresse)).thenReturn(item);

        CommandeRechercheIP cmd = new CommandeRechercheIP(dnsMock, "192.168.0.1");
        cmd.execute();

        // Vérifie que getItem a été appelé
        verify(dnsMock, times(1)).getItem(adresse);
    }

    @Test
    public void testRechercheIPValideIntrouvable() {
        Dns dnsMock = mock(Dns.class);
        AdresseIP adresse = new AdresseIP("10.0.0.1");

        when(dnsMock.getItem(adresse)).thenReturn(null);

        CommandeRechercheIP cmd = new CommandeRechercheIP(dnsMock, "10.0.0.1");
        cmd.execute();

        verify(dnsMock, times(1)).getItem(adresse);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRechercheIPInvalide() {
        Dns dnsMock = mock(Dns.class);

        // Adresse IP invalide déclenche IllegalArgumentException
        CommandeRechercheIP cmd = new CommandeRechercheIP(dnsMock, "999.0.0.1");
        cmd.execute();
    }
}
