import java.util.List;

public class ANN{
	private Register hiddenWeights;
    private Register outputWeights; 
    private Register hiddenBias;
    private Register outputBias;	
	private double learningRate=0.3;
    private int epochs;
	
	public ANN(int nInputs,int nHidden,int nOutputs, int epochs){
		this.hiddenWeights = new Register(nHidden,nInputs);
		this.outputWeights = new Register(nOutputs,nHidden);
		this.hiddenBias = new Register(nHidden,1);
		this.outputBias = new Register(nOutputs,1);
		this.epochs = epochs;
	}
	
	public List<Double> predict(double[] X){
		//Propagation with already stimated W and B
		Register input = Register.fromArray(X);
		Register hidden = Register.multiply(hiddenWeights, input);
		hidden.add(hiddenBias);
		hidden.sigmoid();
		
		Register output = Register.multiply(outputWeights,hidden);
		output.add(outputBias);
		output.sigmoid();
		
		//Classificate - Converts the sigmoid output into a discrete set of values
		Register classification = Register.activate(output);
		return classification.toArray();
	}
	
	
	public void fit(double[][]X,double[][]Y){
        //Iterate over epochs
		for(int i=0;i<epochs;i++){	
            //Iterate over registers
			for(int j=0; j<X.length; j++){
                this.train(X[j], Y[j]);
            }
		}
	}
	
	public void train(double [] X,double [] Y){
		//Feedforward Propagation
		Register input = Register.fromArray(X);
		Register hidden = Register.multiply(hiddenWeights, input);
		hidden.add(hiddenBias);
		hidden.sigmoid();
		
		Register output = Register.multiply(outputWeights,hidden);
		output.add(outputBias);
		output.sigmoid();
		
		//BackPropagation
		Register target = Register.fromArray(Y);
		
		Register error = Register.subtract(target, output);
		Register gradient = output.dsigmoid();
		gradient.multiply(error);
		gradient.multiply(learningRate);
		
		Register hidden_T = Register.transpose(hidden);
		Register who_delta =  Register.multiply(gradient, hidden_T);
		
		outputWeights.add(who_delta);
		outputBias.add(gradient);
		
		Register who_T = Register.transpose(outputWeights);
		Register hidden_errors = Register.multiply(who_T, error);
		
		Register h_gradient = hidden.dsigmoid();
		h_gradient.multiply(hidden_errors);
		h_gradient.multiply(learningRate);
		
		Register i_T = Register.transpose(input);
		Register wih_delta = Register.multiply(h_gradient, i_T);
		
		//Update W and B
		hiddenWeights.add(wih_delta);
		hiddenBias.add(h_gradient);
	}

}