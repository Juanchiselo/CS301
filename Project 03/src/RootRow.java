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
}
