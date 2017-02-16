class Map extends Element {

  // Only store pairs in sequences
  private Sequence sequence;

  //initialize to empty set 
  public Map(){
    sequence = new Sequence(null, null);
  }

  //Get() and Set()
  public Map(Pair pair) {
    this.sequence = new Sequence(pair, null);
  }

  public Pair get_pair(){
    return (Pair)sequence.get_element();
  }
  public Element get_value(){
    Pair pair = (Pair)sequence.get_element();
    return pair.Get();
  }
  public MyChar get_key(){
    Pair pair = (Pair)sequence.get_element();
    return pair.GetKey();
  }

  public MapIterator find(MyChar key){
    int i;
    for (i = 0; i < sequence.length(); i++) {
      Pair pair = (Pair)(sequence.get_sequence_at_pos(i).get_element());
      if ( key == pair.GetKey() ) {
        return new MapIterator( new Map(pair) );
      }
    }
    return end();
  }

  public Map get_next(){
    return new Map((Pair)sequence.get_next().get_element());
  }

  public void Print(){
    this.sequence.Print();
  }

  public void add(Pair inval) {
    int i = 0;
    if (sequence.length() > 0 && sequence.get_element() != null) {
      for (i = 0; i < sequence.length(); i++) {
        if ( inval.lessThan((Pair)sequence.get_sequence_at_pos(i).get_element()) ) {
          break;
        }
      }
    }
    sequence.add(inval, i);
  }

  public MapIterator begin() {
    return new MapIterator(this);
  }

  public MapIterator end() {
    Pair pair = new Pair(new MyChar('e'), new End());
    Map map = new Map(pair);
    return new MapIterator(map);
  }

}
