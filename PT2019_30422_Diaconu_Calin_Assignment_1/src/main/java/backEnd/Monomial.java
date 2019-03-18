package backEnd;

public class Monomial implements Comparable<Monomial>{
    private float coefficient;
    private int exponent;

    public Monomial(float coefficient, int exponent) {
        this.coefficient = coefficient;
        this.exponent = exponent;
    }

    public float getCoefficient() {
        return coefficient;
    }

    public int getExponent() {
        return exponent;
    }

    void setCoefficient(float coefficient) {
        this.coefficient = coefficient;
    }

    public int compareTo(Monomial o) {
        Integer thisExponent = exponent;
        Integer otherExponent = o.getExponent();

        return thisExponent.compareTo(otherExponent);
    }
}
