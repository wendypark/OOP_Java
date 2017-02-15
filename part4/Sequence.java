class Sequence extends Element {

  private Element element;
  private Sequence next;

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

  public SequenceIterator begin() {
    return SequenceIterator(this);
  }
  public SequenceIterator end() {
    return SequenceIterator(get_last_sequence());
  }

  public Sequence GetNext() {
    return next;
  }
  public Sequence GetElement() {
    return next;
  }

  public void Print(){
        System.out.print("[ ");

        //constructed new sequence object head 
        //name for the current instance inside the instance
        Sequence head = this;
        while (head != null){
          head.element.Print();
          System.out.print(" ");
          head = head.next;
        }
        System.out.print("]");
    }

  //returns first element of sequence
  public Element first(){
    return this.element;

  }

  //returns rest of the elements of sequence
  public Sequence rest(){
    return this.next;

  }

  //return number of of elements in sequence object
  public int length(){
    int size = 0;
    Sequence temp = this;
    while (temp != null) {
      temp = temp.next; //moving pointer
      size++;
    }
  
    return size;
  }

  private void assert_pos_is_valid(int pos){
    if (length() < pos || pos < 0) {  
      System.err.println("ERROR: Position " + pos + " does not exist in the sequence."); 
      System.exit(1);
    }
  }

  private Sequence get_last_sequence() {
    return get_sequence_at_pos( length() - 1 );
  }

  private Sequence get_sequence_at_pos(int pos) {
    Sequence target = this;
    int i = 0;
    while ( i < pos ) {
      target = target.next;
      i++;
    }
    return target;
  }

  public void add(Element elem, int pos){
    // assert that position is not out of range ( Allow + 1 since we're adding )
    assert_pos_is_valid(pos + ((pos == 0) ? 0 : -1));

    // if position is 0, insert at first position 
    if (pos == 0) {
      this.next = new Sequence(this.element, this.next);
      this.element = elem;
      return;
    }

    // iterate to before position in sequence
    Sequence target = get_sequence_at_pos(pos - 1);

    // 3 cases:
    //    1. No next sequence
    //    2. Next sequence's element is null (Should this even be allowed behavior?)
    //    3. Next sequence has an element
    if (target.next == null) {
      // no next sequence
      target.next = new Sequence(elem, null);
    } else if (target.next.element == null) {
      // next sequence.element is null
      target.next.element = elem;
    } else{
      // next sequence.element is not null 
      target.next = new Sequence(elem, target.next);
    }
  }

  // NOTE: 
  public void delete(int pos){
    // assert that position is not out of range
    assert_pos_is_valid(pos);

    // if position is 0, delete at first position
    if (pos == 0) {
      // 2 cases:
      //   1. No next node
      //   2. Next node exists
      if (this.next == null) {
        this.element = null;
      } else {
        this.element = this.next.element;
        this.next = this.next.next;
      }
      return;
    }

    // iterate to before position in sequence
    Sequence target = get_sequence_at_pos(pos - 1);

    // 2 cases:
    //    1. No sequence after sequence we're deleting
    //    2. There is a sequence after sequence we're deleting
    if (target.next.next == null) {
      target.next = null;
    } else {
      target.next = target.next.next;
    }
  }
  
    
}
