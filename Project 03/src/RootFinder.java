import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RootFinder
{
    private static RootFinder instance = null;

    public static RootFinder getInstance()
    {
        if(instance == null)
            instance = new RootFinder();
        return instance;
    }

    private RootFinder()
    {
    }

    public ObservableList<RootRow> Bisection(String equation, double a, double b,
                                    double nmax, double epsilon)
    {
        ObservableList<RootRow> data = FXCollections.observableArrayList();

        int n;
        double c = 0;
        double fa = 0;
        double fb = 0;
        double fc = 0;
        double error;

        if(equation.equals("A"))
        {
            fa = EquationEvaluator.getInstance().equationA(a);
            fb = EquationEvaluator.getInstance().equationA(b);
        }
        else
        {
            fa = EquationEvaluator.getInstance().equationB(a);
            fb = EquationEvaluator.getInstance().equationB(b);
        }

        if(fa * fb >= 0)
        {
            Main.mainWindowController.setStatus("ERROR", "Function has same signs at a and b."
                + " a = " + a + ", b = " + b + ", f(a) = " + fa + ", f(b) = " + fb);
            return null;
        }

        error = b - a;

        for(n = 0; n < nmax; n++)
        {
            error = error / 2;
            c = a + error;

            if(equation.equals("A"))
                fc = EquationEvaluator.getInstance().equationA(c);
            else
                fc = EquationEvaluator.getInstance().equationB(c);

            data.add(new RootRow(n, a, b, c, fa, fb, fc, error));

            if(Math.abs(error) < epsilon)
            {
                Main.mainWindowController.setStatus("Status", "Convergence!");
                return data;
            }

            if(fa * fc < 0)
            {
                b = c;
                fb = fc;
            }
            else
            {
                a = c;
                fa = fc;
            }
        }

        return data;
    }

    public ObservableList<RootRow> Newton(String equation, double x,
                                          double nmax, double epsilon)
    {
        ObservableList<RootRow> data = FXCollections.observableArrayList();

        int n;
        double

        return data;
    }
}
