public class Activation {   
    //Sigmoid Function
    public double sigmoid(double[] x, double[] w, double b){
        double z = 0.0;
        for(int i = 0; i < x.length; i++){
            z += x[i] * w[i];
        }
        z += b;
        double s = 1 / (1 + Math.exp(-1 * z));
        return s;
    }

    public boolean activate(double result) {
        //Activate based on a trigger value
        double trigger = 0.5;
        return result > trigger;
    }

    //TODO: This class would admit any function a.e. linear, ramp tangent hyperbolic, etc...
}