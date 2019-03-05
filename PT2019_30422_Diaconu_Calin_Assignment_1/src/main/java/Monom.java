public class Monom {
    private int constanta;
    private int exponent;

    public Monom(int constanta, int exponent) {
        this.constanta = constanta;
        this.exponent = exponent;
    }

    public int getBaza() {
        return constanta;
    }

    public int getExponent() {
        return exponent;
    }
}
