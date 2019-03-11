import java.util.*;

public class Polinom {
    private List<Monom> monomList;

    public Polinom() {
        this.monomList = new ArrayList<Monom>();
    }

    public Polinom(List<Monom> monomList) {
        this.monomList = new ArrayList<Monom>(monomList);
        Collections.sort(this.monomList);
    }

    public Polinom(Polinom polinom) {
        this(polinom.getMonomList());
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

//    static public List<Polinom> impartire(Polinom polinom1, Polinom polinom2) {
//        Polinom cat = new Polinom();
//        Polinom rest = new Polinom();
//
//        for(Monom monom : polinom1.getMonomList()) {
//            rest.add(monom);
//        }
//
//        while(rest.monomList.get(rest.monomList.size() - 1).getExponent() > polinom2.monomList.get(polinom2.monomList.size()).getExponent() ||
//                ((rest.monomList.get(rest.monomList.size() - 1).getExponent() == polinom2.monomList.get(polinom2.monomList.size()).getExponent()) &&
//                        rest.monomList.get(rest.monomList.size() - 1).getConstanta() > polinom2.monomList.get(polinom2.monomList.size()).getConstanta()
//                )) {
//
//        }
//
//        List<Polinom> polinomList = new ArrayList<Polinom>();
//        polinomList.add(cat);
//        polinomList.add(rest);
//
//        return polinomList;
//    }

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
        if(monom.getConstanta() != 0) {
            boolean flag = false;
            for (Monom monom1 : this.monomList) {
                if (monom.getExponent() == monom1.getExponent()) {
                    monom1.setConstanta(monom.getConstanta() + monom1.getConstanta());

                    if (monom1.getConstanta() == 0) {
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
    }

    public void print(){
        for(Monom monom : this.monomList) {
            System.out.println(monom.getConstanta() + " " + monom.getExponent());
        }
        System.out.println();
    }

    public static void main(String[] args) {
        //5x^15+x^8+2x^3+4x-3
        Polinom polinom1 = new Polinom();
        polinom1.add(new Monom(-3, 0));
        polinom1.add(new Monom(4, 1));
        polinom1.add(new Monom(2, 3));
        polinom1.add(new Monom(1, 8));
        polinom1.add(new Monom(5, 15));

        //5x^15+x^10+4x^3
        Polinom polinom2 = new Polinom();
        polinom2.add(new Monom(4, 3));
        polinom2.add(new Monom(1, 10));
        polinom2.add(new Monom(5, 15));
        polinom2.add(new Monom(0, 21));

        //6x^2+2x+5
        Polinom polinom3 = new Polinom();
        polinom3.add(new Monom(6, 2));
        polinom3.add(new Monom(2, 1));
        polinom3.add(new Monom(5, 0));

        Polinom rezultat1 = adunare(polinom1, polinom2);
        Polinom rezultat2 = scadere(polinom1, polinom2);
        Polinom rezultat3 = inmultire(polinom1, polinom2);
        //Polinom rezultat4 = impartire(polinom1, polinom2);
        Polinom rezultat5 = derivare(polinom1);
        Polinom rezultat6 = integrare(polinom3);

        rezultat1.print();
        System.out.println();
        rezultat2.print();
        System.out.println();
        rezultat3.print();
        //System.out.println();
        //rezultat4.print();
        System.out.println();
        rezultat5.print();
        System.out.println();
        rezultat6.print();
    }
}
