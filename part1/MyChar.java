class MyChar extends Element {
	private char c_data;

	//constructor
	//http://www.cis.upenn.edu/~matuszek/General/JavaSyntax/constructors.html
	public MyChar() {
		c_data = '0';
	}
	
	public MyChar(char c_data) {
		this.c_data = c_data;
	}

	public char Get() {
		return c_data;
	}

	public void Set(char val) {
		c_data = val;
	}

}



