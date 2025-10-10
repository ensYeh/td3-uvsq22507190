package fr.uvsq.cprog.collex;

import org.junit.*;
import static org.junit.Assert.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class DnsTest {

    private File tempFile;
    private File configFile;

    @Before
    public void setUp() throws IOException {
        // Créer un fichier DNS temporaire
        tempFile = File.createTempFile("dns", ".txt");
        tempFile.deleteOnExit();

        // Écrire une entrée valide
        List<String> lignes = Arrays.asList("host.domain 192.168.0.1");
        Files.write(tempFile.toPath(), lignes);

        // Créer un fichier config.properties temporaire
        configFile = File.createTempFile("config", ".properties");
        configFile.deleteOnExit();
        try (FileWriter writer = new FileWriter(configFile)) {
            writer.write("dns.file=" + tempFile.getAbsolutePath() + "\n");
        }

        // Remplacer le nom de fichier dans la classe Dns pour le test
        System.setProperty("config.file", configFile.getAbsolutePath());
    }

    @Test
    public void testGetItemParNomMachine() throws IOException {
        Dns dns = new Dns();
        NomMachine nom = new NomMachine("host.domain");
        DnsItem item = dns.getItem(nom);
        assertNotNull(item);
        assertEquals("192.168.0.1", item.getAdresseIP().getIp());
    }

    @Test
    public void testGetItemParAdresseIP() throws IOException {
        Dns dns = new Dns();
        AdresseIP ip = new AdresseIP("192.168.0.1");
        DnsItem item = dns.getItem(ip);
        assertNotNull(item);
        assertEquals("host.domain", item.getNomMachine().getNom());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddItemNomExistant() throws IOException {
        Dns dns = new Dns();
        dns.addItem(new NomMachine("host.domain"), new AdresseIP("192.168.0.2"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddItemIPExistant() throws IOException {
        Dns dns = new Dns();
        dns.addItem(new NomMachine("new.host"), new AdresseIP("192.168.0.1"));
    }

    @Test
    public void testAddItemValide() throws IOException {
        Dns dns = new Dns();
        NomMachine nom = new NomMachine("new.host");
        AdresseIP ip = new AdresseIP("192.168.0.2");
        dns.addItem(nom, ip);

        DnsItem item = dns.getItem(nom);
        assertNotNull(item);
        assertEquals("192.168.0.2", item.getAdresseIP().getIp());
    }
}
