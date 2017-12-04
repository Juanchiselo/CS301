public class DataRow
{
    private String x;
    private String f0;
    private String f1;
    private String f2;
    private String f3;

    public DataRow(String x, String f0, String f1, String f2, String f3) {
        this.x = x;
        this.f0 = f0;
        this.f1 = f1;
        this.f2 = f2;
        this.f3 = f3;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getF0() {
        return f0;
    }

    public void setF0(String f0) {
        this.f0 = f0;
    }

    public String getF1() {
        return f1;
    }

    public void setF1(String f1) {
        this.f1 = f1;
    }

    public String getF2() {
        return f2;
    }

    public void setF2(String f2) {
        this.f2 = f2;
    }

    public String getF3() {
        return f3;
    }

    public void setF3(String f3) {
        this.f3 = f3;
    }
}
