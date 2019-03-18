package jUnit;

import backEnd.Monomial;
import junit.framework.*;

public class MonomialTest extends TestCase {
    public void testGetCoefficient() {
        Monomial monomial = new Monomial(10, 10);
        assertEquals(10.0f, monomial.getCoefficient());

        Monomial monomial1 = new Monomial(0, 10);
        assertEquals(0.0f, monomial1.getCoefficient());

        Monomial monomial2 = new Monomial(-3, 10);
        assertEquals(-3.0f, monomial2.getCoefficient());

        float c = (float) (Math.random() * 100);
        int e = (int) (Math.random() * 100);
        Monomial monomial3 = new Monomial(c, e);
        assertEquals(c, monomial3.getCoefficient());
    }

    public void testGetExponent() {
        Monomial monomial = new Monomial(5, 5);
        assertEquals(5, monomial.getExponent());

        Monomial monomial1 = new Monomial(5, 12);
        assertEquals(12, monomial1.getExponent());

        Monomial monomial2 = new Monomial(5, 0);
        assertEquals(0, monomial2.getExponent());

        float c = (float) (Math.random() * 100);
        int e = (int) (Math.random() * 100);
        Monomial monomial3 = new Monomial(c, e);
        assertEquals(c, monomial3.getExponent());
    }

    public void testCompareTo() {
        for(int i = 0; i < 10; i++) {
            float c1 = (float) (Math.random() * 100);
            float c2 = (float) (Math.random() * 100);

            Integer e1 = (int) (Math.random() * 100);
            Integer e2 = (int) (Math.random() * 100);

            Monomial monomial = new Monomial(c1, e1);
            Monomial monomial1 = new Monomial(c2, e2);

            assertEquals(e1.compareTo(e2), monomial.compareTo(monomial1));
        }
    }
}