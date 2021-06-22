import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import java.util.ArrayList;

public class ANNAgent extends Agent {
  GUI gui;
  ANN neuralNetwork;

  protected void setup() {
    System.out.println("Agent " + getLocalName() + " started.");
    //Creates the data set
    ArrayList<Register> dataSet = new ArrayList<Register>();
    //Patterns for Right x, b, y
    dataSet.add(new Register(new double[] { 0,0,1,0,0,  0,0,1,1,0,  1,1,1,1,1,  1,0,1,1,0,  1,0,1,0,0 }, 0.2, 1));
    dataSet.add(new Register(new double[] { 0,0,1,0,0,  0,0,0,1,0,  1,1,1,1,1,  1,0,0,1,0,  1,0,1,0,0 }, 0.5, 1));
    dataSet.add(new Register(new double[] { 0,0,1,1,0,  0,0,0,1,1,  1,1,1,1,1,  1,0,0,1,1,  1,0,1,1,0 }, 0.9, 1));
    //Patterns for Left x, b, y
    dataSet.add(new Register(new double[] { 0,0,1,0,0,  0,1,1,0,0,  1,1,1,1,1,  0,1,1,0,1,  0,0,1,0,1 }, 0.2, 0));
    dataSet.add(new Register(new double[] { 0,0,1,0,0,  0,1,0,0,0,  1,1,1,1,1,  0,1,0,0,1,  0,0,1,0,1 }, 0.5, 0));
    dataSet.add(new Register(new double[] { 0,1,1,0,0,  1,1,0,0,0,  1,1,1,1,1,  1,1,0,0,1,  0,1,1,0,1 }, 0.9, 0));

    neuralNetwork = new ANN(dataSet);
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
        boolean classification = neuralNetwork.predictClassification(new Register(xValues, 0.5, 0));
        if(classification){
          System.out.println("Turn Right");
        }
        else{
          System.out.println("Turn Left");
        }
      }
    });
  }
}