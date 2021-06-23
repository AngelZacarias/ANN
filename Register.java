import java.util.ArrayList;
import java.util.List;

public class Register{
	public double[][] data;
	public int rows;
    public int columns;
    public Activation activationFunction;
	
	public Register(int rows, int columns){
		data = new double[rows][columns];
		this.rows = rows;
		this.columns = columns;

		//Fills any Register (W or B) with random values between -1 and 1
		for(int i=0; i<rows; i++){
			for(int j=0; j<columns; j++){
				data[i][j]=Math.random()*2-1;
			}
		}
        this.activationFunction = new Activation();
	}
	
	// OPERATIONS TRATED AS MATRIX
	// TODO: Implement a library for this

	public void print(){
		for(int i=0; i<rows; i++){
			for(int j=0; j<columns; j++){
				System.out.print(this.data[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	public void add(int scaler){
		for(int i=0;i<rows;i++){
			for(int j=0;j<columns;j++){
				this.data[i][j]+=scaler;
			}
			
		}
	}
	
	public void add(Register m){
		for(int i=0; i<rows; i++){
			for(int j=0; j<columns; j++){
				this.data[i][j] += m.data[i][j];
			}
		}
	}
	
	public static Register fromArray(double[]x){
		Register temp = new Register(x.length,1);
		for(int i =0;i<x.length;i++){
			temp.data[i][0]=x[i];
        }
		return temp;
	}
	
	public List<Double> toArray(){
		List<Double> temp= new ArrayList<Double>();
		for(int i=0;i<rows;i++){
			for(int j=0;j<columns;j++){
				temp.add(data[i][j]);
			}
		}
		return temp;
	}

	public static Register subtract(Register a, Register b){
		Register temp=new Register(a.rows,a.columns);
		for(int i=0;i<a.rows;i++){
			for(int j=0;j<a.columns;j++){
				temp.data[i][j]=a.data[i][j]-b.data[i][j];
			}
		}
		return temp;
	}

	public static Register transpose(Register a){
		Register temp=new Register(a.columns,a.rows);
		for(int i=0;i<a.rows;i++){
			for(int j=0;j<a.columns;j++){
				temp.data[j][i]=a.data[i][j];
			}
		}
		return temp;
	}

	public static Register multiply(Register a, Register b){
		Register temp=new Register(a.rows,b.columns);
		for(int i=0; i<temp.rows; i++){
			for(int j=0; j<temp.columns; j++){
				double sum=0;
				for(int k=0;k<a.columns;k++){
					sum+=a.data[i][k]*b.data[k][j];
				}
				temp.data[i][j]=sum;
			}
		}
		return temp;
	}
	
	public void multiply(Register a){
		for(int i=0;i<a.rows;i++){
			for(int j=0;j<a.columns;j++){
				this.data[i][j]*=a.data[i][j];
			}
		}
	}
	
	public void multiply(double a){
		for(int i=0;i<rows;i++){
			for(int j=0;j<columns;j++){
				this.data[i][j]*=a;
			}
		}
	}
	
    // ACTIVATION FUNCTIONS
	// TODO: Implement a library for this
	public void sigmoid(){
        for(int i=0;i<rows;i++){
			for(int j=0;j<columns;j++){
				this.data[i][j] = 1/(1+Math.exp(-this.data[i][j]));
            }
		}
	}
	
	public Register dsigmoid(){
		Register temp = new Register(rows,columns);
		for(int i=0;i<rows;i++){
			for(int j=0;j<columns;j++){
				temp.data[i][j] = this.data[i][j] * (1-this.data[i][j]);
            }
		}
		return temp;
	}

    // CLASIFICATION
	// TODO: Implement a library for this probably the same as activations and make it general for any activation function...
    public static Register activate(Register s){
        //Activate based on a trigger value
        double trigger = 0.5;
        Register temp = new Register(s.rows,s.columns);
        for(int i=0;i<s.rows;i++){
			for(int j=0;j<s.columns;j++){
                if(s.data[i][j] > trigger){
                    temp.data[i][j] = 1;
                }
                else{
                    temp.data[i][j] = 0;
                }
            }
		}
        return temp;
    }
}