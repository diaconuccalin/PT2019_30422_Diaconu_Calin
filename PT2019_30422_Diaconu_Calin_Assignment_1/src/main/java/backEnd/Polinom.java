package backEnd;

import java.text.DecimalFormat;
import java.util.*;

public class Polinom implements Comparable<Polinom>{
    private List<Monom> monomList;

    public Polinom() {
        this.monomList = new ArrayList<Monom>();
    }

    public List<Monom> getMonomList() {
        return monomList;
    }

    static public Polinom adunare(Polinom polinom1, Polinom polinom2) {
        Polinom rezultat = new Polinom();

        for(Monom monom : polinom1.getMonomList()) {
            rezultat.add(new Monom (monom.getConstanta(), monom.getExponent()));
        }

        for(Monom monom : polinom2.getMonomList()) {
            rezultat.add(new Monom(monom.getConstanta(), monom.getExponent()));
        }

        return rezultat;
    }

    static public Polinom scadere(Polinom polinom1, Polinom polinom2) {
        Polinom rezultat = new Polinom();

        for(Monom monom : polinom1.getMonomList()) {
            rezultat.add(new Monom(monom.getConstanta(), monom.getExponent()));
        }

        for(Monom monom : polinom2.getMonomList()) {
            rezultat.add(new Monom(-monom.getConstanta(), monom.getExponent()));
        }

        return rezultat;
    }

    static public Polinom inmultire(Polinom polinom1, Polinom polinom2) {
        Polinom rezultat = new Polinom();

        for(Monom monom1 : polinom1.getMonomList()) {
            for(Monom monom2 : polinom2.getMonomList()) {
                rezultat.add(new Monom(
                        monom1.getConstanta() * monom2.getConstanta(),
                        monom1.getExponent() + monom2.getExponent())
                );
            }
        }

        return rezultat;
    }

    static public List<Polinom> impartire(Polinom polinom1, Polinom polinom2) {
        Polinom cat = new Polinom();
        Polinom rest = new Polinom();

        for(Monom monom : polinom1.getMonomList()) {
            rest.add(monom);
        }

        while(rest.compareTo(polinom2) >= 0) {
            float constanta1 = rest.getMonomList().get(rest.getMonomList().size() - 1).getConstanta();
            float constanta2 = polinom2.getMonomList().get(polinom2.getMonomList().size() - 1).getConstanta();
            int exponent1 = rest.getMonomList().get(rest.getMonomList().size() - 1).getExponent();
            int exponent2 = polinom2.getMonomList().get(polinom2.getMonomList().size() - 1).getExponent();

            Monom monom = new Monom(Math.round(constanta1 / constanta2), exponent1 - exponent2);
            Polinom aux = new Polinom();

            cat.add(monom);
            aux.add(monom);
            aux = inmultire(aux, polinom2);
            rest = scadere(rest, aux);
        }

        List<Polinom> polinomList = new ArrayList<Polinom>();
        polinomList.add(cat);
        polinomList.add(rest);

        return polinomList;
    }

    static public Polinom derivare(Polinom polinom) {
        Polinom rezultat = new Polinom();

        for(Monom monom : polinom.getMonomList()) {
            rezultat.add(new Monom(monom.getConstanta()*monom.getExponent(), monom.getExponent() - 1));
        }

        return rezultat;
    }

    static public Polinom integrare(Polinom polinom) {
        Polinom rezultat = new Polinom();

        for(Monom monom : polinom.getMonomList()) {
            rezultat.add(new Monom(monom.getConstanta()/(monom.getExponent()+1), monom.getExponent() + 1));
        }

        return rezultat;
    }

    public void add(Monom monom) {
        boolean flag = false;
        for (Monom monom1 : this.monomList) {
            if (monom.getExponent() == monom1.getExponent()) {
                monom1.setConstanta(monom.getConstanta() + monom1.getConstanta());

                if (monom1.getConstanta() == 0 && monom1.getExponent() != 0) {
                    monomList.remove(monom1);
                }

                flag = true;
                break;
            } else if (monom1.getExponent() > monom.getExponent()) {
                break;
            }
        }

        if (!flag) {
            this.monomList.add(monom);
        }

        Collections.sort(this.monomList);
    }

    public int compareTo(Polinom o) {
        if(this.monomList.size() == 0) {
            if(o.getMonomList().size() == 0)
                return 0;
            else
                return -1;
        }
        if(o.getMonomList().size() == 0)
            return 1;

        Integer exponent1 = this.monomList.get(this.monomList.size() - 1).getExponent();
        Integer exponent2 = o.getMonomList().get(o.getMonomList().size() - 1).getExponent();
        if(exponent1.compareTo(exponent2) == 0) {
            Float constanta1 = this.monomList.get(this.monomList.size() - 1).getConstanta();
            float constanta2 = o.getMonomList().get(o.getMonomList().size() - 1).getConstanta();

            if(constanta1 < 0)
                constanta1 = -constanta1;
            if(constanta2 < 0)
                constanta2 = -constanta2;

            return constanta1.compareTo(constanta2);
        }
        else {
            return exponent1.compareTo(exponent2);
        }
    }

    @Override
    public String toString() {
        String string = "";

        Collections.sort(this.monomList, Collections.<Monom>reverseOrder());

        for(Monom monom : monomList) {
            if(monom.getConstanta() != 0) {
                DecimalFormat df = new DecimalFormat();
                df.setMaximumFractionDigits(2);

                if ((monom == monomList.get(0) || monom.getConstanta() < 0) && monom.getConstanta() != 1) {
                    string = string.concat(df.format(monom.getConstanta()) + "");
                } else if (monom.getConstanta() != 1 || monom.getExponent() == 0) {
                    string = string.concat("+" + df.format(monom.getConstanta()));
                } else {
                    string = string.concat("+");
                }

                if (monom.getExponent() > 1) {
                    string = string.concat("x^" + monom.getExponent());
                } else if (monom.getExponent() == 1) {
                    string = string.concat("x");
                }
            }
        }

        if(string.length() > 0 && string.charAt(0) == '+')
            string = string.substring(1);

        if(string.length() == 0)
            string = "0";

        Collections.sort(this.monomList);

        return string;
    }
}
