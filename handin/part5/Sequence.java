class Sequence extends Element {

  Element element;
  Sequence next;

  //initialize to empty set 
  public Sequence(){
    element = null;
    next = null; 
  }

  //Get() and Set()
  public Sequence(Element element, Sequence next) {
    this.element = element;
    this.next = next;
  }

  public Element first() {
    return element;
  }

  public Sequence rest() {
    return next;
  }

  public int length(){
    int size = 0;
    Sequence temp = this;
    while (temp != null) {
      temp = temp.next;
      size++;
    }
    return size;
  }

  // Will not be using this function within implementation to avoid confusion between get element and get sequence
  public Element index(int pos) {
    return getElementAtIndex(pos);
  }

  public void add(Element elem, int pos) {
    // Case: position is root
    if (pos == 0) {
      if (element == null) {
        // Replace null value at sequence
        element = elem;
      } else {
        // Create new sequence at root
        next = new Sequence(element, next);
        this.element = elem;
      }
      return;
    }

    // Case: position is not root
    Sequence sequenceBeforePosition = getSequenceAtIndex(pos - 1);
    if (sequenceBeforePosition.next == null || sequenceBeforePosition.next.element != null) {
      // Shift over sequence at pos and insert new sequence, or just create new sequence at end
      sequenceBeforePosition.next = new Sequence(elem, sequenceBeforePosition.next);
    } else {
      // Replace null value at sequence
      sequenceBeforePosition.next.element = elem;
    }
  }

  public void delete(int pos) {
    if (pos_is_valid(pos)) {
      // Case: position is root
      if (pos == 0) {
        this.element = next.element;
        this.next = next.next;
        return;
      }

      // Case: position is not root
      Sequence sequenceBeforePosition = getSequenceAtIndex(pos - 1);
      sequenceBeforePosition.next = sequenceBeforePosition.next.next;

    }
  }

  public void Print(){
      System.out.print("[ ");
      Sequence temp = this;
      while (temp != null){
        if (temp.element != null) {
          temp.element.Print();
        } else {
          System.out.print("'null'");
        }
        System.out.print(' ');
        temp = temp.next;
      }
      System.out.print("]");
  }

  public Sequence flatten() {
    // emptyRoot has no element. The actual flattenned sequence to be outputted will be at emptyRoot.next
    // I did this to simplify the base step in the while loop
    Sequence emptyRoot = new Sequence();
    Sequence flattenedTail = emptyRoot;
    Sequence sequence = this;
    while (sequence != null){
      // If current element is not a Sequence
      if (sequence.element == null || !(sequence.element instanceof Sequence) ) {
        flattenedTail.next = new Sequence(sequence.element, sequence.next);
        flattenedTail = flattenedTail.next;
      // If current element is a Sequence
      } else {
        // Flatten subsequence, append to output sequence, and move sequence to the end of output sequence
        flattenedTail.next = ((Sequence)sequence.element).flatten();
        flattenedTail = flattenedTail.getSequenceAtIndex(flattenedTail.length() - 1);
      }
      sequence = sequence.next;
    }
    return emptyRoot.next;
  }

  public Sequence copy() {

    Element elementCopy = null;
    Sequence nextSequenceCopy = null;
    if (element instanceof Sequence) {
      elementCopy = ((Sequence)element).copy();
    } else if (element instanceof MyChar) {
      elementCopy = ((MyChar)element).copy();
    } else if (element instanceof MyInteger) {
      elementCopy = ((MyInteger)element).copy();
    }

    if (next != null) {
      nextSequenceCopy = next.copy();
    }

    return new Sequence(elementCopy, nextSequenceCopy);
  }


  //
  // Part 4: Iterator Functions
  //

  public SequenceIterator begin() {
    return new SequenceIterator(this);
  }

  public SequenceIterator end() {
    return SequenceIterator.end();
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

  public Element getElementAtIndex(int pos) {
    return getSequenceAtIndex(pos).element;
  }

  public Sequence getSequenceAtIndex(int pos) {
    assert_pos_is_valid(pos);
    Sequence temp = this;
    int i = 0;
    while ( i < pos ) {
      temp = temp.next;
      i++;
    }
    return temp;
  }

}