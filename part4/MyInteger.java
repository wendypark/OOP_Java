class MyInteger extends Element {
	private int i_data;

	//constructor
	public MyInteger() {
		i_data = 0;
	}

	public MyInteger(int i_data) {
		this.i_data = i_data;
	}

	public int Get() {
		return i_data;

	}
	public void Set(int val) {
		i_data = val; 
	}

	public MyInteger copy() {
		return new MyInteger(Get());
	}
	
	public void Print(){
		System.out.print(i_data);
	}

}