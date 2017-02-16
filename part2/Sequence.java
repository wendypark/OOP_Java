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

  public void add(Element elem, int pos) {

    // Case: position is root
    if (pos == 0) {
      next = new Sequence(element, next);
      this.element = elem;
      return;
    }

    // Case: position is not root
    Sequence sequenceBeforePosition = getSequenceAtIndex(pos - 1);
    if (sequenceBeforePosition.next == null) {
      // Add new sequence to end
      sequenceBeforePosition.next = new Sequence(elem, null);
    } else if (sequenceBeforePosition.next.element == null) {
      // Replace null value at sequence
      sequenceBeforePosition.next.element = elem;
    } else {
      // Shift over sequence at pos and insert new sequence
      sequenceBeforePosition.next = new Sequence(elem, sequenceBeforePosition.next);
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