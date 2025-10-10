package fr.uvsq.cprog.collex;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.IOException;

public class CommandeAjoutItemTest {

    @Test
    public void testAjoutReussi() throws IOException {
        Dns dnsMock = mock(Dns.class);
        CommandeAjoutItem cmd = new CommandeAjoutItem(dnsMock, "192.168.0.1", "host.domain");

        cmd.execute();

        // Vérifie que addItem a été appelé correctement
        verify(dnsMock, times(1)).addItem(any(NomMachine.class), any(AdresseIP.class));
    }

    @Test
    public void testAjoutNomInvalide() throws IOException {
        Dns dnsMock = mock(Dns.class);
        CommandeAjoutItem cmd = new CommandeAjoutItem(dnsMock, "192.168.0.1", "host..domain");

        cmd.execute();

        // dns.addItem ne doit jamais être appelé
        verify(dnsMock, never()).addItem(any(NomMachine.class), any(AdresseIP.class));
    }

    @Test
    public void testAjoutIpInvalide() throws IOException {
        Dns dnsMock = mock(Dns.class);
        CommandeAjoutItem cmd = new CommandeAjoutItem(dnsMock, "999.0.0.1", "host.domain");

        cmd.execute();

        // dns.addItem ne doit jamais être appelé
        verify(dnsMock, never()).addItem(any(NomMachine.class), any(AdresseIP.class));
    }

    @Test
    public void testIOException() throws IOException {
        Dns dnsMock = mock(Dns.class);
        doThrow(new IOException("Erreur écriture")).when(dnsMock)
                .addItem(any(NomMachine.class), any(AdresseIP.class));

        CommandeAjoutItem cmd = new CommandeAjoutItem(dnsMock, "192.168.0.1", "host.domain");

        cmd.execute();

        // Vérifie que addItem a été appelé une fois malgré l'exception
        verify(dnsMock, times(1)).addItem(any(NomMachine.class), any(AdresseIP.class));
    }
}
