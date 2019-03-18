package jUnit;

import backEnd.Monomial;
import backEnd.Polynomial;
import junit.framework.TestCase;

import java.util.List;

public class PolynomialTest extends TestCase {
    public void testAddition() {
        Polynomial p1 = new Polynomial();
        p1.add(new Monomial(0, 0));

        Polynomial p2 = new Polynomial();
        p2.add(new Monomial(0, 0));

        Polynomial p3 = Polynomial.addition(p1, p2);

        assertEquals(1, p3.getMonomialList().size());
        assertEquals(0, p3.getMonomialList().get(0).getExponent());
        assertEquals(0.0f, p3.getMonomialList().get(0).getCoefficient());


        Polynomial p4 = new Polynomial();
        p4.add(new Monomial(1, 0));

        Polynomial p5 = Polynomial.addition(p2, p4);

        assertEquals(1, p5.getMonomialList().size());
        assertEquals(0, p5.getMonomialList().get(0).getExponent());
        assertEquals(1.0f, p5.getMonomialList().get(0).getCoefficient());


        Polynomial p6 = new Polynomial();
        p6.add(new Monomial(5, 3));
        p6.add(new Monomial(12, 8));
        p6.add(new Monomial(12, 8));
        p6.add(new Monomial(4, 0));
        p6.add(new Monomial(127, 2121));

        Polynomial p7 = new Polynomial();
        p7.add(new Monomial(4, 1));
        p7.add(new Monomial(2, 3));
        p7.add(new Monomial(45, 7));
        p7.add(new Monomial(44, 4));

        Polynomial p8 = Polynomial.addition(p6, p7);

        Polynomial p9 = new Polynomial();
        p9.add(new Monomial(4, 1));
        p9.add(new Monomial(2, 3));
        p9.add(new Monomial(45, 7));
        p9.add(new Monomial(44, 4));
        p9.add(new Monomial(5, 3));
        p9.add(new Monomial(12, 8));
        p9.add(new Monomial(12, 8));
        p9.add(new Monomial(4, 0));
        p9.add(new Monomial(127, 2121));

        assertEquals(0, p9.compareTo(p8));
    }

    public void testSubtraction() {
        Polynomial p1 = new Polynomial();
        p1.add(new Monomial(0, 0));

        Polynomial p2 = new Polynomial();
        p2.add(new Monomial(0, 0));

        Polynomial p3 = Polynomial.subtraction(p1, p2);

        assertEquals(1, p3.getMonomialList().size());
        assertEquals(0, p3.getMonomialList().get(0).getExponent());
        assertEquals(0.0f, p3.getMonomialList().get(0).getCoefficient());


        Polynomial p4 = new Polynomial();
        p4.add(new Monomial(1, 0));

        Polynomial p5 = Polynomial.subtraction(p2, p4);

        assertEquals(1, p5.getMonomialList().size());
        assertEquals(0, p5.getMonomialList().get(0).getExponent());
        assertEquals(-1.0f, p5.getMonomialList().get(0).getCoefficient());


        Polynomial p6 = new Polynomial();
        p6.add(new Monomial(5, 3));
        p6.add(new Monomial(12, 8));
        p6.add(new Monomial(12, 8));
        p6.add(new Monomial(4, 0));
        p6.add(new Monomial(127, 2121));

        Polynomial p7 = new Polynomial();
        p7.add(new Monomial(4, 1));
        p7.add(new Monomial(2, 3));
        p7.add(new Monomial(45, 7));
        p7.add(new Monomial(44, 4));

        Polynomial p8 = Polynomial.subtraction(p6, p7);

        Polynomial p9 = new Polynomial();
        p9.add(new Monomial(-4, 1));
        p9.add(new Monomial(-2, 3));
        p9.add(new Monomial(-45, 7));
        p9.add(new Monomial(-44, 4));
        p9.add(new Monomial(5, 3));
        p9.add(new Monomial(12, 8));
        p9.add(new Monomial(12, 8));
        p9.add(new Monomial(4, 0));
        p9.add(new Monomial(127, 2121));

        assertEquals(0, p9.compareTo(p8));
    }

    public void testMultiplication() {
        for(int i = 0; i < 10; i++) {
            int e1 = (int) (Math.random() * 100);
            int e2 = (int) (Math.random() * 100);

            float c1 = (float) Math.random() * 100;
            float c2 = (float) Math.random() * 100;

            Polynomial p1 = new Polynomial();
            Polynomial p2 = new Polynomial();

            p1.add(new Monomial(c1, e1));
            p2.add(new Monomial(c2, e2));

            Polynomial p3 = new Polynomial();
            p3.add(new Monomial(c1 * c2, e1 + e2));

            assertEquals(0, p3.compareTo(Polynomial.multiplication(p1, p2)));
        }
    }

    public void testDivision() {
        Polynomial p1 = new Polynomial();
        Polynomial p2 = new Polynomial();

        p1.add(new Monomial(12, 4));
        p1.add(new Monomial(-6, 3));
        p1.add(new Monomial(0, 2));
        p1.add(new Monomial(2, 1));
        p1.add(new Monomial(7, 0));

        p2.add(new Monomial(3, 2));
        p2.add(new Monomial(2, 0));

        List<Polynomial> polynomialList = Polynomial.division(p1, p2);

        Polynomial c = new Polynomial();
        Polynomial r = new Polynomial();

        c.add(new Monomial(4, 2));
        c.add(new Monomial(-2, 1));
        c.add(new Monomial(-3, 0));

        r.add(new Monomial(1, 2));
        r.add(new Monomial(6, 1));
        r.add(new Monomial(13, 0));

        assertEquals(0, c.compareTo(polynomialList.get(0)));
        assertEquals(0, r.compareTo(polynomialList.get(1)));
    }

    public void testDerivative() {
        for(int i = 0; i < 10; i++) {
            int e = (int) (Math.random() * 100);
            float c = (float) Math.random() * 100;

            Polynomial p1 = new Polynomial();
            Polynomial p2 = new Polynomial();

            p1.add(new Monomial(c, e));
            p2.add(new Monomial(c * e, e - 1));

            assertEquals(0, p2.compareTo(Polynomial.derivative(p1)));
        }
    }

    public void testAntiderivative() {
        for(int i = 0; i < 10; i++) {
            int e = (int) (Math.random() * 100);
            float c = (float) Math.random() * 100;

            Polynomial p1 = new Polynomial();
            Polynomial p2 = new Polynomial();

            p1.add(new Monomial(c, e));
            p2.add(new Monomial(c / (e + 1), e + 1));

            assertEquals(0, p2.compareTo(Polynomial.antiderivative(p1)));
        }
    }

    public void testCompareTo() {
        for(int i = 0; i < 10; i++) {
            int e1 = (int) (Math.random() * 100);
            int e2 = (int) (Math.random() * 100);

            float c1 = (float) Math.random() * 100;
            float c2 = (float) Math.random() * 100;

            Polynomial p1 = new Polynomial();
            Polynomial p2 = new Polynomial();

            Monomial m1 = new Monomial(c1, e1);
            Monomial m2 = new Monomial(c2, e2);

            p1.add(new Monomial(c1, e1));
            p2.add(new Monomial(c2, e2));

            assertEquals(m1.compareTo(m2), p1.compareTo(p2));
        }
    }
}
