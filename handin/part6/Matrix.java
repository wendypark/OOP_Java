public class Matrix extends Sequence {

	int rowsize;
	int colsize;

	// constructor for creating a matrix of specific number of rows and columns
	public Matrix(int rowsize, int colsize) {
		// Create Sequences
		next = Matrix.createSequence(colsize - 1);
		for (SequenceIterator itr = begin(); !itr.equal(end()); itr.advance()) {
			itr.set( Matrix.createSequence(rowsize) );
		}
		this.rowsize = rowsize;
		this.colsize = colsize;
		// Initialize to Zero
		for (int i=0; i < rowsize; i++) {
			for (int j=0; j < colsize; j++) {
				Set(i, j, 0);
			}
		}
	}

	private static Sequence createSequence(int length) {
		// emptyRoot is only a holder for output
		Sequence emptyRoot = new Sequence();
		Sequence sequence = emptyRoot;
		for (int i=0; i<length; i++) {
			sequence.next = new Sequence();
			sequence = sequence.next;
		}
		return emptyRoot.next;
	}

	// set the value of an element
	public void Set(int rowsize, int colsize, int value) {
		assert_rowsize_colsize(rowsize, colsize);
		Sequence column = (Sequence)getElementAtIndex(colsize);
		Sequence target = column.getSequenceAtIndex(rowsize);
		target.element = new MyInteger(value);
	} 

	// get the value of an element
	public int Get(int rowsize, int colsize) {
		assert_rowsize_colsize(rowsize, colsize);
		Sequence column = (Sequence)getElementAtIndex(colsize);
		Sequence target = column.getSequenceAtIndex(rowsize);
		return ((MyInteger)target.element).Get();
	} 

	// return the sum of two matrices: mat & this
	public Matrix Sum(Matrix mat) {
		Matrix matrix = new Matrix(rowsize, colsize);
		for (int i=0; i < rowsize; i++) {
			for (int j=0; j < colsize; j++) {
				matrix.Set(i, j, this.Get(i, j) + mat.Get(i, j));
			}
		}
		return matrix;
	} 

	// return the product of two matrices: mat & this
	public Matrix Product(Matrix mat) {
		if (this.colsize != mat.rowsize) {
      System.out.println("Matrix dimensions incompatible for Product"); 
      System.exit(1);
		}

		Matrix matrix = new Matrix(this.rowsize, mat.colsize);

		// Multiply corresponding rows and columns for matrix multiplication
		for (int row=0; row < this.rowsize; row++) {
			for (int col=0; col < mat.colsize; col++) {
				// Multiply and summate corresponding row and column
				int sum = 0;
				for (int i=0; i < mat.rowsize; i++) {
					// System.out.println(this.Get(row, i));
					// System.out.println(this.Get(i, col));
					sum += this.Get(row, i) * mat.Get(i, col);
				}
				matrix.Set(row, col, sum);
			}
		}
		return matrix;
	} 

	// print the elements of matrix
	public void Print() {
		for (int i=0; i < rowsize; i++) {
			System.out.print("[ ");
			for (int j=0; j < colsize; j++) {
				System.out.print(Get(i, j) + " ");
			}
			System.out.println("]");
		}
	} 

	private void assert_rowsize_colsize(int rowsize, int colsize){
    if ( rowsize > this.rowsize || colsize > this.colsize ) {  
      System.err.println("ERROR: rowsize, colsize " + rowsize + ", " + colsize + " does not exist in the sequence."); 
      System.exit(1);
    }
	}
}