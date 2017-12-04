
public class Fraction
{
    private static Fraction instance = null;

    public static Fraction getInstance()
    {
        if(instance == null)
            instance = new Fraction();
        return instance;
    }

    private Fraction()
    {
    }

    public double evaluate(String fraction)
    {
        String[] fractionParts = fraction.split("[/]");
        double numerator = Double.valueOf(fractionParts[0]);
        double denominator = Double.valueOf(fractionParts[1]);

        return numerator / denominator;
    }

    public String add(String... fractions)
    {
        String fraction = "";
        int[][] values = new int[fractions.length][2];
        int gcd = Integer.MIN_VALUE;

        for(int i = 0; i < fractions.length; i++)
        {
            String[] fractionParts = fractions[i].split("[/]");
            int numerator = Integer.valueOf(fractionParts[0]);
            int denominator = Integer.valueOf(fractionParts[1]);

            values[i][0] = numerator;
            values[i][1] = denominator;

            if(denominator > gcd)
                gcd = denominator;
        }

        int ratio;
        int numerator = 0;
        for(int i = 0; i < values.length; i++)
        {
            ratio = gcd / values[i][1];
            values[i][0] = values[i][0] * ratio;
            values[i][1] = gcd;

            numerator += values[i][0];
        }

        fraction = String.valueOf(numerator) + "/" + String.valueOf(gcd);

        return fraction;
    }
}
