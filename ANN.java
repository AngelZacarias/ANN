import java.util.Random;

public class ANN {   
    //Number of layers and components in each one
    private static int layers = 3; // 3
    private static int nInputs = 25;
    private static int nHidden = 5;
    private static int nOutputs = 2;
    //Weights
    private static double w[][];
    //Bias
    private static double b[][];
    //X inputs
    //Y outputs 2 values

    //Constructor
    public ANN(){
        System.out.println("Initializing Artifitial Neural Network...");
        //Init the weight with random values
        
        //Init bias with random values

        //
    }

    //Method to train the SLR / Find the constants
    public static void train(double[][] data){

    }

    public static void porpagate(){
        double a[][] = {{0},{0},{0},{1},{0},{0},
                            {0},{1},{1},{0},{0},
                            {1},{1},{1},{1},{1},
                            {0},{1},{1},{0},{1},
                            {0},{0},{1},{0},{1},};
        System.out.println(a);
        
        for(int l = 0; l <= layers; l++){
            //TODO: z[l] = w[l] * a[l-1] + b[l]
            MatrixOperations matrixOpeartion = new MatrixOperations();
            double[] z = matrixOpeartion.add(matrixOpeartion.dotProduct(a[l], w[l]), b[l]);
            Activation activation = new Activation();
            a = activation.logistic(z);
            System.out.println(a[l][0]);
        }
    }

    public static void main(String[] args){
        System.out.println("ANN");
        ANN ann = new ANN();
        ann.porpagate();
    }
}