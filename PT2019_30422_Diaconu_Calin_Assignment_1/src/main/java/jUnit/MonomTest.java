package jUnit;

import backEnd.Monom;
import junit.framework.*;

public class MonomTest extends TestCase {
    public void testGetConstanta() {
        Monom monom = new Monom(10, 10);
        assertEquals(10.0f, monom.getConstanta());

        Monom monom1 = new Monom(0, 10);
        assertEquals(0.0f, monom1.getConstanta());

        Monom monom2 = new Monom(-3, 10);
        assertEquals(-3.0f, monom2.getConstanta());

        float c = (float) (Math.random() * 100);
        int e = (int) (Math.random() * 100);
        Monom monom3 = new Monom(c, e);
        assertEquals(c, monom3.getConstanta());
    }

    public void testGetExponent() {
        Monom monom = new Monom(5, 5);
        assertEquals(5, monom.getExponent());

        Monom monom1 = new Monom(5, 12);
        assertEquals(12, monom1.getExponent());

        Monom monom2 = new Monom(5, 0);
        assertEquals(0, monom2.getExponent());

        float c = (float) (Math.random() * 100);
        int e = (int) (Math.random() * 100);
        Monom monom3 = new Monom(c, e);
        assertEquals(c, monom3.getExponent());
    }

    public void testCompareTo() {
        for(int i = 0; i < 10; i++) {
            float c1 = (float) (Math.random() * 100);
            float c2 = (float) (Math.random() * 100);

            Integer e1 = (int) (Math.random() * 100);
            Integer e2 = (int) (Math.random() * 100);

            Monom monom = new Monom(c1, e1);
            Monom monom1 = new Monom(c2, e2);

            assertEquals(e1.compareTo(e2), monom.compareTo(monom1));
        }
    }
}