import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import java.util.ArrayList;
import java.util.List;

public class ANNAgent extends Agent {
  GUI gui;
  ANN neuralNetwork;

  protected void setup() {
    System.out.println("Agent " + getLocalName() + " started.");
    //Creates the data set
    double [][] dataSetX = {
      { 0,0,1,0,0,  0,0,1,1,0,  1,1,1,1,1,  1,0,1,1,0,  1,0,1,0,0 },
      { 0,0,1,0,0,  0,0,0,1,0,  1,1,1,1,1,  1,0,0,1,0,  1,0,1,0,0 },
      { 0,0,1,1,0,  0,0,0,1,1,  1,1,1,1,1,  1,0,0,1,1,  1,0,1,1,0 },
      
      { 0,0,1,0,0,  0,1,1,0,0,  1,1,1,1,1,  0,1,1,0,1,  0,0,1,0,1 },
      { 0,0,1,0,0,  0,1,0,0,0,  1,1,1,1,1,  0,1,0,0,1,  0,0,1,0,1 },
      { 0,1,1,0,0,  1,1,0,0,0,  1,1,1,1,1,  1,1,0,0,1,  0,1,1,0,1 }

    };
    double [][] dataSetY= {
			{ 1, 1 },
      { 1, 1 },
      { 1, 1 },
      { 0, 0 },
      { 0, 0 },
      { 0, 0 }
	  };
    int nInputs = 25;
    int nHidenNeurons = 5;
    int nOutputs = 2;
    int epochs = 500;
    neuralNetwork = new ANN(nInputs, nHidenNeurons, nOutputs, epochs);
    
    //Train the Neural Network
    neuralNetwork.fit(dataSetX, dataSetY);

    gui = new GUI(this);
    gui.showGui();
  }

  public void predict(String xData) {
    addBehaviour(new OneShotBehaviour() {
      @Override
      public void action() {
        //Create the data set for x
        double[] xValues = new double[25];
        int i = 0;
        for(String value: xData.split(",")){
          xValues[i] = Double.parseDouble(value);
          i++;
        }
        List<Double> classification = neuralNetwork.predict(xValues);
        if(classification.get(0)==1.0 && classification.get(1)==1.0){
          System.out.println("Turn Right");
        }
        else if(classification.get(0)==0.0 && classification.get(1)==0.0){
          System.out.println("Turn Left");
        }
        else{
          System.out.println("Unknown");
        }
      }
    });
  }
}