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

    public double equationB(double x)
    {
        // f(x) = x + 10 - xcosh(50/x)
        return x + 10 - (x * Math.cosh(50 / x));
    }
}
