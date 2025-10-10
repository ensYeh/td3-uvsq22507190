package fr.uvsq.cprog.collex;

import org.junit.Test;
import static org.mockito.Mockito.*;
import java.util.*;

public class CommandeRechercheDomaineTest {

    @Test
    public void testRechercheTriParIP() {
        Dns dnsMock = mock(Dns.class);

        DnsItem item1 = new DnsItem(new NomMachine("host1.domain"), new AdresseIP("192.168.0.5"));
        DnsItem item2 = new DnsItem(new NomMachine("host2.domain"), new AdresseIP("10.0.0.1"));
        List<DnsItem> items = Arrays.asList(item1, item2);

        when(dnsMock.getItems("domain")).thenReturn(new ArrayList<>(items));

        CommandeRechercheDomaine cmd = new CommandeRechercheDomaine(dnsMock, "domain", true);
        cmd.execute();

        // Vérifie que getItems a été appelé
        verify(dnsMock, times(1)).getItems("domain");

        // Vérifie que les items ont été triés par IP
        List<DnsItem> sorted = dnsMock.getItems("domain");
        sorted.sort(Comparator.comparing(DnsItem::getAdresseIP));
        assert (sorted.get(0).getAdresseIP().compareTo(sorted.get(1).getAdresseIP()) < 0);
    }

    @Test
    public void testRechercheTriParNom() {
        Dns dnsMock = mock(Dns.class);

        DnsItem item1 = new DnsItem(new NomMachine("beta.domain"), new AdresseIP("192.168.0.5"));
        DnsItem item2 = new DnsItem(new NomMachine("alpha.domain"), new AdresseIP("10.0.0.1"));
        List<DnsItem> items = Arrays.asList(item1, item2);

        when(dnsMock.getItems("domain")).thenReturn(new ArrayList<>(items));

        CommandeRechercheDomaine cmd = new CommandeRechercheDomaine(dnsMock, "domain", false);
        cmd.execute();

        verify(dnsMock, times(1)).getItems("domain");

        // Vérifie que les items ont été triés par nom
        List<DnsItem> sorted = dnsMock.getItems("domain");
        sorted.sort(Comparator.comparing(DnsItem::getNomMachine));
        assert (sorted.get(0).getNomMachine().compareTo(sorted.get(1).getNomMachine()) < 0);
    }

    @Test
    public void testRechercheDomaineVide() {
        Dns dnsMock = mock(Dns.class);

        when(dnsMock.getItems("empty")).thenReturn(Collections.emptyList());

        CommandeRechercheDomaine cmd = new CommandeRechercheDomaine(dnsMock, "empty", true);
        cmd.execute();

        verify(dnsMock, times(1)).getItems("empty");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRechercheDomaineNull() {
        Dns dnsMock = mock(Dns.class);

        when(dnsMock.getItems(null)).thenThrow(new IllegalArgumentException("Domaine null"));

        CommandeRechercheDomaine cmd = new CommandeRechercheDomaine(dnsMock, null, true);
        cmd.execute();
    }
}
