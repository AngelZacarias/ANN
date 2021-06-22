public class Register{
    private double[] xValues;
    private double b;
    private int output;

    public Register(double[] x, double b, int y){
        this.xValues = x;
        this.b = b;
        this.output = y;
    }

    public double[] getX(){
        return this.xValues;
    }

    public double getB(){
        return this.b;
    }

    public void setY(int y){
        this.output = y;
    }

    public double getY(){
        return this.output;
    }
}