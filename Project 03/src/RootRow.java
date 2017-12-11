public class RootRow
{
    private int n;
    private double a;
    private double b;
    private double c;
    private double fa;
    private double fb;
    private double fc;
    private double approximateError;
    private double xn;
    private double fxn;
    private double fxnP;
    private double xnP1;
    private double xnM1;
    private double fxnM1;

    // Bisection Method Constructor
    public RootRow(int n, double a, double b, double c, double fa, double fb, double fc,
                   double approximateError) {
        this.n = n;
        this.a = a;
        this.b = b;
        this.c = c;
        this.fa = fa;
        this.fb = fb;
        this.fc = fc;
        this.approximateError = approximateError;
    }

    // Newton-Raphson Method Constructor
    public RootRow(int n, double xn, double fxn, double fxnP, double xnP1, double approximateError) {
        this.n = n;
        this.xn = xn;
        this.fxn = fxn;
        this.fxnP = fxnP;
        this.xnP1 = xnP1;
        this.approximateError = approximateError;
    }

    // Secant Method Constructor
    public RootRow(int n, double xnM1, double xn, double xnP1, double fxn, double fxnM1, double approximateError) {
        this.n = n;
        this.approximateError = approximateError;
        this.xn = xn;
        this.fxn = fxn;
        this.xnP1 = xnP1;
        this.xnM1 = xnM1;
        this.fxnM1 = fxnM1;
    }

    public int getN()
    {
        return n;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }

    public double getFa() {
        return fa;
    }

    public double getFb() {
        return fb;
    }

    public double getFc() {
        return fc;
    }

    public double getApproximateError() {
        return approximateError;
    }

    public double getXn() {
        return xn;
    }

    public double getFxn() {
        return fxn;
    }

    public double getFxnP() {
        return fxnP;
    }

    public double getXnP1() {
        return xnP1;
    }

    public double getFxnM1() {
        return fxnM1;
    }

    public double getXnM1() {
        return xnM1;
    }
}
