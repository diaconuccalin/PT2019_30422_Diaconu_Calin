package backEnd;

import java.text.DecimalFormat;
import java.util.*;

public class Polynomial implements Comparable<Polynomial>{
    private List<Monomial> monomialList;

    public Polynomial() {
        this.monomialList = new ArrayList<Monomial>();
    }

    public List<Monomial> getMonomialList() {
        return monomialList;
    }

    public void add(Monomial monomial) {
        boolean flag = false;
        for (Monomial monomial1 : this.monomialList) {
            if (monomial.getExponent() == monomial1.getExponent()) {
                monomial1.setCoefficient(monomial.getCoefficient() + monomial1.getCoefficient());

                if (monomial1.getCoefficient() == 0 && monomial1.getExponent() != 0) {
                    monomialList.remove(monomial1);
                }

                flag = true;
                break;
            } else if (monomial1.getExponent() > monomial.getExponent()) {
                break;
            }
        }

        if (!flag) {
            this.monomialList.add(monomial);
        }

        Collections.sort(this.monomialList);
    }

    static public Polynomial addition(Polynomial polynomial1, Polynomial polynomial2) {
        Polynomial result = new Polynomial();

        for(Monomial monomial : polynomial1.getMonomialList()) {
            result.add(new Monomial(monomial.getCoefficient(), monomial.getExponent()));
        }

        for(Monomial monomial : polynomial2.getMonomialList()) {
            result.add(new Monomial(monomial.getCoefficient(), monomial.getExponent()));
        }

        return result;
    }

    static public Polynomial subtraction(Polynomial polynomial1, Polynomial polynomial2) {
        Polynomial result = new Polynomial();

        for(Monomial monomial : polynomial1.getMonomialList()) {
            result.add(new Monomial(monomial.getCoefficient(), monomial.getExponent()));
        }

        for(Monomial monomial : polynomial2.getMonomialList()) {
            result.add(new Monomial(-monomial.getCoefficient(), monomial.getExponent()));
        }

        return result;
    }

    static public Polynomial multiplication(Polynomial polynomial1, Polynomial polynomial2) {
        Polynomial result = new Polynomial();

        for(Monomial monomial1 : polynomial1.getMonomialList()) {
            for(Monomial monomial2 : polynomial2.getMonomialList()) {
                result.add(new Monomial(
                        monomial1.getCoefficient() * monomial2.getCoefficient(),
                        monomial1.getExponent() + monomial2.getExponent())
                );
            }
        }

        return result;
    }

    static public List<Polynomial> division(Polynomial polynomial1, Polynomial polynomial2) {
        Polynomial quotient = new Polynomial();
        Polynomial remainder = new Polynomial();

        for(Monomial monomial : polynomial1.getMonomialList()) {
            remainder.add(monomial);
        }

        while(remainder.compareTo(polynomial2) >= 0) {
            float coefficient1 = remainder.getMonomialList().get(remainder.getMonomialList().size() - 1).getCoefficient();
            float coefficient2 = polynomial2.getMonomialList().get(polynomial2.getMonomialList().size() - 1).getCoefficient();
            int exponent1 = remainder.getMonomialList().get(remainder.getMonomialList().size() - 1).getExponent();
            int exponent2 = polynomial2.getMonomialList().get(polynomial2.getMonomialList().size() - 1).getExponent();

            Monomial monomial = new Monomial(Math.round(coefficient1 / coefficient2), exponent1 - exponent2);

            Polynomial aux = new Polynomial();

            quotient.add(monomial);
            aux.add(monomial);
            aux = multiplication(aux, polynomial2);
            remainder = subtraction(remainder, aux);
        }

        List<Polynomial> polynomialList = new ArrayList<Polynomial>();
        polynomialList.add(quotient);
        polynomialList.add(remainder);

        return polynomialList;
    }

    static public Polynomial derivative(Polynomial polynomial) {
        Polynomial result = new Polynomial();

        for(Monomial monomial : polynomial.getMonomialList()) {
            result.add(new Monomial(monomial.getCoefficient()* monomial.getExponent(), monomial.getExponent() - 1));
        }

        return result;
    }

    static public Polynomial antiderivative(Polynomial polynomial) {
        Polynomial result = new Polynomial();

        for(Monomial monomial : polynomial.getMonomialList()) {
            result.add(new Monomial(monomial.getCoefficient()/(monomial.getExponent()+1), monomial.getExponent() + 1));
        }

        return result;
    }

    public int compareTo(Polynomial o) {
        if(this.monomialList.size() == 0) {
            if(o.getMonomialList().size() == 0)
                return 0;
            else
                return -1;
        }
        if(o.getMonomialList().size() == 0)
            return 1;

        Integer exponent1 = this.monomialList.get(this.monomialList.size() - 1).getExponent();
        Integer exponent2 = o.getMonomialList().get(o.getMonomialList().size() - 1).getExponent();
        if(exponent1.compareTo(exponent2) == 0) {
            Float coefficient1 = this.monomialList.get(this.monomialList.size() - 1).getCoefficient();
            float coefficient2 = o.getMonomialList().get(o.getMonomialList().size() - 1).getCoefficient();

            if(coefficient1 < 0)
                coefficient1 = -coefficient1;
            if(coefficient2 < 0)
                coefficient2 = -coefficient2;

            return coefficient1.compareTo(coefficient2);
        }
        else {
            return exponent1.compareTo(exponent2);
        }
    }

    @Override
    public String toString() {
        String string = "";

        Collections.sort(this.monomialList, Collections.<Monomial>reverseOrder());

        for(Monomial monomial : monomialList) {
            if(monomial.getCoefficient() != 0) {
                DecimalFormat df = new DecimalFormat();
                df.setMaximumFractionDigits(2);

                if ((monomial == monomialList.get(0) || monomial.getCoefficient() < 0) && monomial.getCoefficient() != 1) {
                    string = string.concat(df.format(monomial.getCoefficient()) + "");
                } else if (monomial.getCoefficient() != 1 || monomial.getExponent() == 0) {
                    string = string.concat("+" + df.format(monomial.getCoefficient()));
                } else {
                    string = string.concat("+");
                }

                if (monomial.getExponent() > 1) {
                    string = string.concat("x^" + monomial.getExponent());
                } else if (monomial.getExponent() == 1) {
                    string = string.concat("x");
                }
            }
        }

        if(string.length() > 0 && string.charAt(0) == '+')
            string = string.substring(1);

        if(string.length() == 0)
            string = "0";

        Collections.sort(this.monomialList);

        return string;
    }
}
