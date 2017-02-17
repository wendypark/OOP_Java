class Map extends Pair {

  Pair pair;
  Map next;

  //initialize to empty set 
  public Map(){
    pair = null;
    next = null; 
  }

  public Map(Pair pair, Map next){
    this.pair = pair;
    this.next = next; 
  }

  public Pair first() {
    return pair;
  }

  public Map rest() {
    return next;
  }

  public int length(){
    int size = 0;
    Map temp = this;
    while (temp != null) {
      temp = temp.next;
      size++;
    }
    return size;
  }

  // Will not be using this function within implementation to avoid confusion between get pair and get sequence
  public Pair index(int pos) {
    return getPairAtIndex(pos);
  }

  public MapIterator find(MyChar key) {
    Map matchingMap = null;
    for (int i = 0; i < length(); i++) {
      Map tempMap = getMapAtIndex(i);
      if (tempMap.pair.key.Get() == key.Get()) {
        matchingMap = tempMap;
        break;
      }
    }
    if (matchingMap == null) {
      return end();
    }

    return new MapIterator(matchingMap);
  }

  public void add(Pair pair) {
    if (this.pair == null) {
      this.pair = pair;
      return;
    }

    int i = 0;
    for (; i < length(); i++) {
      Pair tempPair = getPairAtIndex(i);
      if (tempPair == null) {
        System.err.println("ERROR: Position " + i + " is null? This shouldn't happen..."); 
        System.exit(1);
      }
      if (pair.lessThan(tempPair)) {
        break;
      }
    }

    if (i == 0) {
      this.next = new Map(this.pair, this.next);
      this.pair = pair;
    } else {
      Map mapBeforeTarget = getMapAtIndex(i - 1);
      mapBeforeTarget.next = new Map(pair, mapBeforeTarget.next);
    }
  }

  public void Print(){
      System.out.print("[ ");
      Map temp = this;
      while (temp != null){
        if (temp.pair != null) {
          temp.pair.Print();
        } else {
          System.out.print("'null'");
        }
        System.out.print(' ');
        temp = temp.next;
      }
      System.out.print("]");
  }

  //
  // Part 4: Iterator Functions
  //

  public MapIterator begin() {
    return new MapIterator(this);
  }

  public MapIterator end() {
    return MapIterator.end();
  }


  //
  // Additional Functions
  //

  private boolean pos_is_valid(int pos) {
    return ( pos < length() && pos >= 0 );
  }

  private void assert_pos_is_valid(int pos){
    if ( !pos_is_valid( pos ) ) {  
      System.err.println("ERROR: Position " + pos + " does not exist in the sequence."); 
      System.exit(1);
    }
  }

  public Pair getPairAtIndex(int pos) {
    return getMapAtIndex(pos).pair;
  }

  public Map getMapAtIndex(int pos) {
    assert_pos_is_valid(pos);
    Map temp = this;
    int i = 0;
    while ( i < pos ) {
      temp = temp.next;
      i++;
    }
    return temp;
  }

}