public class EquationEvaluator
{
    private static EquationEvaluator instance = null;

    public static EquationEvaluator getInstance()
    {
        if(instance == null)
            instance = new EquationEvaluator();
        return instance;
    }

    private EquationEvaluator()
    {
    }

    public double evaluate(String equation, double x)
    {
        return 0;
    }

    public double equationA(double x)
    {
        // f(x) = 2x^3 - 11.7x^2 + 17.7x - 5
        return (2 * Math.pow(x, 3)) - (11.7 * Math.pow(x, 2))
                + (17.7 * x) - 5;
    }

    public double equationAP(double x)
    {
        // f'(x) = 6x^2 - 23.4x + 17.7
        return (6 * Math.pow(x, 2)) - (23.4 * x) + 17.7;
    }

    public double equationB(double x)
    {
        // f(x) = x + 10 - xcosh(50/x)
        return x + 10 - (x * Math.cosh(50 / x));
    }

    public double equationBP(double x)
    {
        // ((50sinh(50/x))/x) - cosh(50/x) + 1
        return ((50 * Math.sinh(50 / x)) / x) - Math.cosh(50 / x) + 1;
    }
}
