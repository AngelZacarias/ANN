class MatrixOperations{

    public double[] dotProduct(double[] a, double[] b){
        int ni = a.length;
        double[] sum = {};
        for (int i = 0; i < ni; i++) {
            sum[i] = a[i] * b[i];   
        }
        return sum;
    }

    public double[] add(double[] a, double[] b){
        int ni = a.length;
        double[] sum = {};
        for (int i = 0; i < ni; i++) {
            sum[i] = a[i] + b[i];
        }
        return sum;
    }
}