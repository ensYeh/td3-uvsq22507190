package fr.uvsq.cprog.collex;

import org.junit.Test;
import static org.junit.Assert.*;

public class AdresseIPTest {

    @Test
    public void testAdresseValide() {
        AdresseIP ip = new AdresseIP("192.168.1.1");
        assertEquals("192.168.1.1", ip.getIp());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAdresseInvalideValSup() {
        new AdresseIP("256.100.50.25");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAdresseInvalideTropCourte() {
        new AdresseIP("192.168.1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAdresseInvalideTexte() {
        new AdresseIP("abc.def.ghi.jkl");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAdresseInvalideNull() {
        new AdresseIP(null);
    }

    @Test
    public void testCompareTo() {
        AdresseIP ip1 = new AdresseIP("10.0.0.1");
        AdresseIP ip2 = new AdresseIP("10.0.0.2");
        AdresseIP ip3 = new AdresseIP("10.0.0.1");

        assertTrue(ip1.compareTo(ip2) < 0);
        assertTrue(ip2.compareTo(ip1) > 0);
        assertEquals(0, ip1.compareTo(ip3));
    }

    @Test
    public void testEqualsAndHashCode() {
        AdresseIP ip1 = new AdresseIP("127.0.0.1");
        AdresseIP ip2 = new AdresseIP("127.0.0.1");
        AdresseIP ip3 = new AdresseIP("192.168.1.1");

        assertEquals(ip1, ip2);
        assertNotEquals(ip1, ip3);
        assertEquals(ip1.hashCode(), ip2.hashCode());
    }

    @Test
    public void testIsValid() {
        assertTrue(AdresseIP.isValid("8.8.8.8"));
        assertFalse(AdresseIP.isValid("999.1.1.1"));
        assertFalse(AdresseIP.isValid("192.168.1"));
        assertFalse(AdresseIP.isValid("abc.def.ghi.jkl"));
        assertFalse(AdresseIP.isValid(null));
    }
}