package backEnd;

public class Monom implements Comparable<Monom>{
    private float constanta;
    private int exponent;

    public Monom(float constanta, int exponent) {
        this.constanta = constanta;
        this.exponent = exponent;
    }

    public float getConstanta() {
        return constanta;
    }

    public int getExponent() {
        return exponent;
    }

    void setConstanta(float constanta) {
        this.constanta = constanta;
    }

    public int compareTo(Monom o) {
        Integer thisExponent = exponent;
        Integer otherExponent = o.getExponent();

        return thisExponent.compareTo(otherExponent);
    }
}
