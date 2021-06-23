# ANN - Artificial Neural Network

Artificial Neural Network using feedforward propagation backpropagation to predict traffic signals.

## Input: 

25 pixels (5x5 matrix) separated by comma where 1 means line and 0 empty space

### Example:
0,0,1,0,0,0,0,1,1,0,1,1,1,1,1,1,0,1,1,0,1,0,1,0,0

### Wich represents "Turn Right"
0,0,1,0,0,  
0,0,1,1,0,  
1,1,1,1,1,  
1,0,1,1,0,  
1,0,1,0,0

## Output

2 bits representing:
- [1 , 1]: Turn Right
- [0 , 0]: Turn Left

## How To Run in CMD
Execute: 
- javac Main.java 
- javac Register.java 
- javac ANN.java 
- and then java Main

## How to Run in JADE
Compile with:
- javac -classpath lib/jade.jar -d classes src/examples/ANN/*.java src/examples/ANN/*.java

Execute with:
- java -cp lib/jade.jar;classes jade.Boot -gui -port 3100 -agents ANN:ANNAgent