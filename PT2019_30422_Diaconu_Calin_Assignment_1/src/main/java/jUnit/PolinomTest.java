package jUnit;

import backEnd.Monom;
import backEnd.Polinom;
import junit.framework.TestCase;

import java.util.List;

public class PolinomTest extends TestCase {
    public void testAdunare() {
        Polinom p1 = new Polinom();
        p1.add(new Monom(0, 0));

        Polinom p2 = new Polinom();
        p2.add(new Monom(0, 0));

        Polinom p3 = Polinom.adunare(p1, p2);

        assertEquals(1, p3.getMonomList().size());
        assertEquals(0, p3.getMonomList().get(0).getExponent());
        assertEquals(0.0f, p3.getMonomList().get(0).getConstanta());


        Polinom p4 = new Polinom();
        p4.add(new Monom(1, 0));

        Polinom p5 = Polinom.adunare(p2, p4);

        assertEquals(1, p5.getMonomList().size());
        assertEquals(0, p5.getMonomList().get(0).getExponent());
        assertEquals(1.0f, p5.getMonomList().get(0).getConstanta());


        Polinom p6 = new Polinom();
        p6.add(new Monom(5, 3));
        p6.add(new Monom(12, 8));
        p6.add(new Monom(12, 8));
        p6.add(new Monom(4, 0));
        p6.add(new Monom(127, 2121));

        Polinom p7 = new Polinom();
        p7.add(new Monom(4, 1));
        p7.add(new Monom(2, 3));
        p7.add(new Monom(45, 7));
        p7.add(new Monom(44, 4));

        Polinom p8 = Polinom.adunare(p6, p7);

        Polinom p9 = new Polinom();
        p9.add(new Monom(4, 1));
        p9.add(new Monom(2, 3));
        p9.add(new Monom(45, 7));
        p9.add(new Monom(44, 4));
        p9.add(new Monom(5, 3));
        p9.add(new Monom(12, 8));
        p9.add(new Monom(12, 8));
        p9.add(new Monom(4, 0));
        p9.add(new Monom(127, 2121));

        assertEquals(0, p9.compareTo(p8));
    }

    public void testScadere() {
        Polinom p1 = new Polinom();
        p1.add(new Monom(0, 0));

        Polinom p2 = new Polinom();
        p2.add(new Monom(0, 0));

        Polinom p3 = Polinom.scadere(p1, p2);

        assertEquals(1, p3.getMonomList().size());
        assertEquals(0, p3.getMonomList().get(0).getExponent());
        assertEquals(0.0f, p3.getMonomList().get(0).getConstanta());


        Polinom p4 = new Polinom();
        p4.add(new Monom(1, 0));

        Polinom p5 = Polinom.scadere(p2, p4);

        assertEquals(1, p5.getMonomList().size());
        assertEquals(0, p5.getMonomList().get(0).getExponent());
        assertEquals(-1.0f, p5.getMonomList().get(0).getConstanta());


        Polinom p6 = new Polinom();
        p6.add(new Monom(5, 3));
        p6.add(new Monom(12, 8));
        p6.add(new Monom(12, 8));
        p6.add(new Monom(4, 0));
        p6.add(new Monom(127, 2121));

        Polinom p7 = new Polinom();
        p7.add(new Monom(4, 1));
        p7.add(new Monom(2, 3));
        p7.add(new Monom(45, 7));
        p7.add(new Monom(44, 4));

        Polinom p8 = Polinom.scadere(p6, p7);

        Polinom p9 = new Polinom();
        p9.add(new Monom(-4, 1));
        p9.add(new Monom(-2, 3));
        p9.add(new Monom(-45, 7));
        p9.add(new Monom(-44, 4));
        p9.add(new Monom(5, 3));
        p9.add(new Monom(12, 8));
        p9.add(new Monom(12, 8));
        p9.add(new Monom(4, 0));
        p9.add(new Monom(127, 2121));

        assertEquals(0, p9.compareTo(p8));
    }

    public void testInmultire() {
        for(int i = 0; i < 10; i++) {
            int e1 = (int) (Math.random() * 100);
            int e2 = (int) (Math.random() * 100);

            float c1 = (float) Math.random() * 100;
            float c2 = (float) Math.random() * 100;

            Polinom p1 = new Polinom();
            Polinom p2 = new Polinom();

            p1.add(new Monom(c1, e1));
            p2.add(new Monom(c2, e2));

            Polinom p3 = new Polinom();
            p3.add(new Monom(c1 * c2, e1 + e2));

            assertEquals(0, p3.compareTo(Polinom.inmultire(p1, p2)));
        }
    }

    public void testImpartire() {
        Polinom p1 = new Polinom();
        Polinom p2 = new Polinom();

        p1.add(new Monom(12, 4));
        p1.add(new Monom(-6, 3));
        p1.add(new Monom(0, 2));
        p1.add(new Monom(2, 1));
        p1.add(new Monom(7, 0));

        p2.add(new Monom(3, 2));
        p2.add(new Monom(2, 0));

        List<Polinom> polinomList = Polinom.impartire(p1, p2);

        Polinom c = new Polinom();
        Polinom r = new Polinom();

        c.add(new Monom(4, 2));
        c.add(new Monom(-2, 1));
        c.add(new Monom(-3, 0));

        r.add(new Monom(1, 2));
        r.add(new Monom(6, 1));
        r.add(new Monom(13, 0));

        assertEquals(0, c.compareTo(polinomList.get(0)));
        assertEquals(0, r.compareTo(polinomList.get(1)));
    }

    public void testDerivare() {
        for(int i = 0; i < 10; i++) {
            int e = (int) (Math.random() * 100);
            float c = (float) Math.random() * 100;

            Polinom p1 = new Polinom();
            Polinom p2 = new Polinom();

            p1.add(new Monom(c, e));
            p2.add(new Monom(c * e, e - 1));

            assertEquals(0, p2.compareTo(Polinom.derivare(p1)));
        }
    }

    public void testIntegrare() {
        for(int i = 0; i < 10; i++) {
            int e = (int) (Math.random() * 100);
            float c = (float) Math.random() * 100;

            Polinom p1 = new Polinom();
            Polinom p2 = new Polinom();

            p1.add(new Monom(c, e));
            p2.add(new Monom(c / (e + 1), e + 1));

            assertEquals(0, p2.compareTo(Polinom.integrare(p1)));
        }
    }

    public void testCompareTo() {
        for(int i = 0; i < 10; i++) {
            int e1 = (int) (Math.random() * 100);
            int e2 = (int) (Math.random() * 100);

            float c1 = (float) Math.random() * 100;
            float c2 = (float) Math.random() * 100;

            Polinom p1 = new Polinom();
            Polinom p2 = new Polinom();

            Monom m1 = new Monom(c1, e1);
            Monom m2 = new Monom(c2, e2);

            p1.add(new Monom(c1, e1));
            p2.add(new Monom(c2, e2));

            assertEquals(m1.compareTo(m2), p1.compareTo(p2));
        }
    }
}
