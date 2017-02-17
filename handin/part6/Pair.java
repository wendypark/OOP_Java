class Pair extends Element {

  MyChar key;
  Element value;

  //constructor
  public Pair() {
    key = null;
    value = null;
  }

  public Pair(MyChar key, Element value) {
    this.key = key;
    this.value = value;
  }

  public Element Get(){
    return this.value;
  }

  public MyChar GetKey(){
    return this.key;
  }

  public void Print(){
    System.out.print("(");
    this.key.Print();
    System.out.print(" ");
    this.value.Print();
    System.out.print(")");
  }

  public boolean lessThan(Pair pair) {
    return this.key.Get() < pair.key.Get();
  }

  public boolean greaterThan(Pair pair) {
    return this.key.Get() > pair.key.Get();
  }

  public boolean equal(Pair pair) {
    return this.key.Get() == pair.key.Get();
  }

}