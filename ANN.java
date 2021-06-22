import java.util.Random;
import java.util.ArrayList;

public class ANN {  
    private Activation activationFunction;
    private ArrayList<Register> dataSet;
    private double[] weights;
    private double learningRate;
    private int epochs;
    
    //Constructor
    public ANN(ArrayList<Register> data){
        System.out.println("Initializing Artifitial Neural Network...");
        this.epochs = 500;
        this.learningRate = 0.5;
        this.activationFunction = new Activation();
        this.dataSet = data;
        this.weights = initWeightsToZero(this.dataSet.get(0).getX().length);
        System.out.println("Training Artifitial Neural Network...");
        train();
    }

    private double[] initWeightsToZero(int size){
        double[] w = new double[size];
        for (int i = 0; i < size; i++) {
            w[i] = 0;
        }
        return w;
    }

    public void train(){
        this.weights = calculateWeights();
        System.out.println("Training has been finalized...");
    }

    public double predictRegression(Register register){
        double regression = activationFunction.sigmoid(register.getX(), this.weights, register.getB());
        return regression;
    }

    public boolean predictClassification(Register register){
        double regression = activationFunction.sigmoid(register.getX(), this.weights, register.getB());
        boolean classification = activationFunction.activate(regression);
        return classification;
    }

    private double[] calculateWeights(){
        //Initialize the Weights as the algorithm indicates
        int wSize = this.dataSet.get(0).getX().length;
        double[] W = initWeightsToZero(wSize);
        //Iterate over the epochs
        for(int i = 0; i < this.epochs; i++){
            double[] iterWj = new double[wSize];
            //Find W for this iteration
            for (int j = 0; j < wSize; j++) {
                iterWj[j] = W[j] - this.learningRate * resultDifference(W, j);
            }
            W = iterWj;
        }
        return W;
    }

    private double resultDifference(double[] w, int j) {
        double iterationResult = 0.0;
        double fxi = 0.0;
        double yi = 0.0;
        double xij = 0.0;
        //Iterate over all the register to get Sum
        for (int i = 0; i < this.dataSet.size(); i++) {
            //Gets the register and estimate the result
            Register register = this.dataSet.get(i);
            fxi = activationFunction.sigmoid(register.getX(), w, register.getB());
            yi = register.getY();
            xij = register.getX()[j];
            //Apply the equation
            iterationResult += (fxi - yi) * xij;
        }
        return iterationResult;
    }
}