import java.util.List;

public class Main {
	//Or Function
	double[][] X = {
		{0,0},
		{1,0},
		{0,1},
		{1,1}
	};
	double[][] Y = {
		{0},{1},{1},{0}
	};

	public static void main(String[] args){
		ANN nn = new ANN(2, 12, 1, 50000);
		nn.fit(X, Y);
		double [][] input = {
			{0,0},{0,1},{1,0},{1,1}	
		};

		for(double d[]:input){
			List<Double> output = nn.predict(d);
			System.out.println(output.toString());
		}		
	}
}