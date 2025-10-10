package fr.uvsq.cprog.collex;

import org.junit.Test;
import static org.junit.Assert.*;

public class NomMachineTest {

    @Test
    public void testNomValide() {
        NomMachine nm = new NomMachine("host.domain");
        assertEquals("host.domain", nm.getNom());

        NomMachine nm2 = new NomMachine("HOST.Subdomain.Domain");
        assertEquals("host.subdomain.domain", nm2.getNom()); // normalisation en minuscules
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNomTropCourt() {
        new NomMachine("host"); // moins de 2 parties
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNomTropLong() {
        new NomMachine("a.b.c.d"); // plus de 3 parties
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNomPartieVide() {
        new NomMachine("host..domain"); // partie vide
    }

    @Test
    public void testCompareTo() {
        NomMachine nm1 = new NomMachine("alpha.domain");
        NomMachine nm2 = new NomMachine("beta.domain");
        NomMachine nm3 = new NomMachine("alpha.domain");

        assertTrue(nm1.compareTo(nm2) < 0);
        assertTrue(nm2.compareTo(nm1) > 0);
        assertEquals(0, nm1.compareTo(nm3));
    }

    @Test
    public void testEqualsAndHashCode() {
        NomMachine nm1 = new NomMachine("machine.domain");
        NomMachine nm2 = new NomMachine("machine.domain");
        NomMachine nm3 = new NomMachine("other.domain");

        assertEquals(nm1, nm2);
        assertNotEquals(nm1, nm3);
        assertEquals(nm1.hashCode(), nm2.hashCode());
    }

    @Test
    public void testToString() {
        NomMachine nm = new NomMachine("Host.Domain");
        assertEquals("host.domain", nm.toString());
    }
}