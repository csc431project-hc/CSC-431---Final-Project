class Matrix {

    //Class variables
    private int rows;
    private int cols;
    
    private double[][] data;

    //Constructor to create the empty matrix
    public Matrix(int rowsIn, int colsIn){
	//Iterator variables
	int cCount, rCount;

	rows = rowsIn;
	cols = colsIn; 

	data = new double[rows][cols];	

    }

    //Constructor to fill matrix with a double array
    public Matrix(int rowsIn, int colsIn, double[][] fill){
	//Iterator variables
	int cCount, rCount;

	rows = rowsIn;
	cols = colsIn; 

	data = fill.clone();

    }

    //Constructor to add a single array
    public Matrix(int rowsIn, int colsIn, double[] fill){
	//Iterator variables
	int cCount, rCount;

	rows = rowsIn;
	cols = colsIn; 

	data = new double[rows][cols];

	//If there is only one column, fill the column
	if (cols == 1){
	    for (rCount = 0; rCount < rows; ++rCount){
		data[rCount][0] = fill[rCount];
	    }  
	}
	else{ //Else, fill the first row
	    data[0] = fill;	    
	}
    }

    //Function to return an item
    public double getItem (int row, int col){   
	return data[row][col];
    }

    //Function to set an item
    public double setItem (int row, int col, double value){
	data[row][col] = value;
	return value;
    }

    //Function to return a row
    public Matrix row (int rowIn){

	double[] rowVals = new double[cols];
	
	System.arraycopy(data[rowIn], 0, rowVals, 0, data[rowIn].length);

	return new Matrix(1, cols, rowVals); 
    }

    //Function to return a column
    public Matrix col (int colIn){

	int rCount;
	
	double[] colVals = new double[rows];
	
	for (rCount = 0; rCount < rows; ++rCount){
	    colVals[rCount] = data[rCount][colIn];
	}
	
	return new Matrix(rows, 1, colVals); 
    }	

    //Function to return the matrix as a list
    //One row at a time
    public double[] as_list (){
	int rCount, cCount, counter = 0;
	double[] retVal = new double[cols*rows];
	
	for (rCount = 0; rCount < rows; rCount++){
	    for (cCount = 0; cCount < cols; cCount++){
		retVal[counter] = data[rCount][cCount];
		counter++;
	    }
	} 

	return retVal;
    }

    //Function to return the array as a list
    public String toString(){
	double[] retVal = this.as_list();

	return java.util.Arrays.toString(retVal);
    }

    //Function to create the identity matrix
    public Matrix identity(int rows){
	int rCount;
	Matrix retMatrix = new Matrix(rows, rows);

	for (rCount = 0; rCount < rows; ++rCount){
	    retMatrix.setItem(rCount, rCount, 1);
	}

	return retMatrix;
    }

    //Lays the values along the diagonal
    public Matrix diagonal(double[] vals){
	int rCount;
	Matrix retMatrix = new Matrix(vals.length, vals.length);

	for (rCount = 0; rCount < vals.length; ++rCount){
	    retMatrix.setItem(rCount, rCount, vals[rCount]);
	}

	return retMatrix;
    }

    //Adds to matrices together provided they have the same dimensions
    public static Matrix add (Matrix matA, Matrix matB){
	
	int rCount, cCount;
	Matrix matC = null;

	if (checkDimensions(matA, matB)){
	    
	    matC = new Matrix(matA.rows, matB.rows);

	    for (rCount = 0; rCount < matC.rows; rCount++){
		for (cCount = 0; cCount < matC.cols; cCount++){
		    matC.setItem(rCount, cCount, matA.getItem(rCount, cCount) + matB.getItem(rCount, cCount));
		}
	    }	   

	}

	return matC;
    }

    //Helper function to ensure the dimensions are the same
    public static boolean checkDimensions(Matrix matA, Matrix matB){
	if ((matA.cols == matB.cols) && (matA.rows == matB.rows)){
	   return true;
	 }
	else{
	    return false;
	} 
    }
    
    //Subtracts the matrices, element by element, provided that the two matrices are
    //of the same dimensions 
    public static Matrix subtract(Matrix matA, Matrix matB){

    	int rCount, cCount;
    	Matrix matS = null;

    	if (checkDimensions(matA, matB)){

    	    matS = new Matrix(matA.rows, matB.rows);

    	    for (rCount = 0; rCount < matS.rows; rCount++){
    	    	for (cCount = 0; cCount < matS.cols; cCount++){
    	    		matS.setItem(rCount, cCount, matA.getItem(rCount, cCount)
    	    									- matB.getItem(rCount, cCount));
    		}
    	    }	   

    	}

    	return matS;
        }    
        
     //Adds the matrices in reverse order
    //Note:Dimensions checked by add function already
    public static Matrix rAdd(Matrix matA, Matrix matB)
    {
    	return add(matB,matA);
    	
    }
        
    //Function that reverses the order of subtraction for the two matrices by copying and negating all values of 
    //the first matrix into a new matrix, which is added to the second matrix
    //Note:Dimension is checked already by add function
    public static Matrix rSubtract(Matrix matA, Matrix matB){

    	Matrix matRS = null;

    	    matRS = new Matrix(matA.rows, matB.rows);
    	    Matrix matNA = new Matrix(matA.rows, matA.cols);
    	   
    	    for ( int rCount=0; rCount<matNA.rows; rCount++)
    	    {
    	      for ( int cCount=0; cCount<matNA.cols; cCount++ )
    	    {
    	    	  matNA.setItem(rCount,cCount, (-1) *matA. getItem(rCount,cCount));
    	    }
    	    
    	    }

    	      matRS = add(matNA, matB);

    	return matRS;
        }    
    
  //A function to negate a matrix
    public static Matrix negate(Matrix matA){
    
    	Matrix matNA = new Matrix(matA.rows, matA.cols);
    	for ( int rCount=0; rCount<matNA.rows; rCount++)
	    {
	      for ( int cCount=0; cCount<matNA.cols; cCount++ )
	      {
	    	  matNA.setItem(rCount,cCount, (-1) *matA. getItem(rCount,cCount));
	      }
	    
	    }
    	return matNA;
    }
    //A function to print the matrix
    public void printMatrix(){
	int cCount, rCount;
	String currLine;

	for (rCount = 0; rCount < rows; ++rCount){
	    
	    currLine = "["; 
	    
	    for (cCount = 0; cCount < cols; ++cCount){
		currLine += data[rCount][cCount] + " ";
	    }

	    currLine += "]";

	    System.out.println(currLine);
	}

    }    

    public static void main(String[] args){

	//Using this main class for testing
	Matrix testMatrix = new Matrix(6, 6);
	double testVal = 45.4543243;

	testMatrix.setItem(1, 1, testVal);
	testMatrix.setItem(1, 2, testVal);

	Matrix newMatrix = testMatrix.col(1);

	double[] vals = new double[4];
	vals[0] = 0;
	vals[1] = 1;
	vals[2] = 2;
	vals[3] = 3;

	testMatrix = newMatrix.diagonal(vals);
	newMatrix = newMatrix.identity(4);
	
	Matrix threeMatrix = Matrix.add(testMatrix, newMatrix);
	Matrix testSMatrix = Matrix.subtract(testMatrix, newMatrix);
	Matrix testRSMatrix = Matrix.rSubtract(testMatrix, newMatrix);
	Matrix testRAMatrix = Matrix.rAdd(testMatrix, newMatrix);
	Matrix testNMatrix = Matrix.negate(testMatrix);
	
	System.out.println("\n Matrices Addition");
	threeMatrix.printMatrix();
	System.out.println("\n Subtracted Matrices");
	testSMatrix.printMatrix();
	System.out.println("\n Reversed Matrices Subtraction");
	testRSMatrix.printMatrix();
	System.out.println("\n Reversed Matrices Addition");
	testRAMatrix.printMatrix();
	System.out.println("\n Negated Matrix");
	testNMatrix.printMatrix();
	
    }
}