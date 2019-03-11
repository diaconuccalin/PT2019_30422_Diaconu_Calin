public class Monom implements Comparable<Monom>{
    private int constanta;
    private int exponent;

    public Monom(int constanta, int exponent) {
        this.constanta = constanta;
        this.exponent = exponent;
    }

    public Monom(Monom monom) {
        this.constanta = monom.getConstanta();
        this.exponent = monom.getExponent();
    }

    public int getConstanta() {
        return constanta;
    }

    public int getExponent() {
        return exponent;
    }

    public void setConstanta(int constanta) {
        this.constanta = constanta;
    }

    public int compareTo(Monom o) {
        Integer thisExponent = exponent;
        Integer otherExponent = o.getExponent();

        return thisExponent.compareTo(otherExponent);
    }

    public Monom negative() {
        return new Monom(-this.constanta, this.exponent);
    }
}
