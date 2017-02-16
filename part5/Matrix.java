public class Matrix extends Sequence {

	private int [][]  matrix;
	private int rowsize;
	private int colsize;

	// constructor for creating a matrix of specific number of rows and columns
	public Matrix(int rowsize, int colsize) {
		this.rowsize = rowsize;
		this.colsize = colsize;

	}

	// set the value of an element
	public void Set(int rowsize, int colsize, int value) {
		matrix[rowsize][colsize] = value;

	} 

	// get the value of an element
	public int Get(int rowsize, int colsize) {
		return this.matrix[rowsize][colsize];

	} 

	// return the sum of two matrices: mat & this
	public Matrix Sum(Matrix mat) {

	} 

	// return the product of two matrices: mat & this
	public Matrix Product(Matrix mat) {

	} 

	// print the elements of matrix
	public void Print() {

	} 