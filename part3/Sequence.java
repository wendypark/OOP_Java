class Sequence extends Element {

	private Element element;
	private Sequence next;

  // public Sequence() {
  //   SequenceNode head = this.element;
  //   SequenceNode tail = this.next;
  // }

  /*Class SequenceNode() {
    element e;
    SN next;
  }
  public Sequence() {
    SN head;
    SN tail;

  }*/

  public Sequence() {
    element = null;
    next = null;
  }

	//Get() and Set()
	public Sequence(Element element, Sequence next) {
		this.element = element;
		this.next = next;
	}

	public void Print(){
        System.out.print("[ ");
        //constructed new sequence object head 
        //name for the current instance inside the instance
        Sequence head = this;
        while (head != null){
        	head.element.Print();
        	System.out.print(' ');
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

  //create pointer exception error
  public Sequence get_sequence_at_pos(int pos) {
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
    
    Sequence current = this; 
    Sequence head = null; //will store sequence before pos

    //references index pos of current sequence
    int i = 0;
    while ( i < pos && current != null) {
      head = current;
      current = current.next;
      i++;
    }

    //Cases:
    //    1) sequence before pos is empty and pos is not empty
    //    2) sequence before pos is empty and pos is empty
    //    3) sequence before pos is not empty 
    if (head == null && current.element != null) {
        current.next = new Sequence(current.element, current.next);
        current.element = elem;
    }
    else if (head == null && current.element == null) { 
      current.element = elem;
    }
    else {
      head.next = new Sequence(elem, current);
    }
    
	}

  // NOTE: 
  public void delete(int pos){
    // assert that position is not out of range
    assert_pos_is_valid(pos);

    // if position is 0, delete at first position
    if (pos == 0) {
      // 2 cases:
      //     1. No next node
      //     2. Next node exists
      if (this.next == null) {
        this.element = null;
      } 
      else {
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
    } 
    else {
      target.next = target.next.next;
    }
	}

  //used to access a particular element of a Sequence object
  public Element index(int pos) {
    //error flagging 
    assert_pos_is_valid(pos);
    //gets sequence at given position
    Sequence target = get_sequence_at_pos(pos);
    //returns element of sequence 
    return target.element;

  }

  //flatten other subsequences into new sequence 
  public Sequence flatten() {

    //new sequence 
    Sequence flattened = null; //flatsequence
    Sequence current = this; //currentnode
    int pos = 0;
    while (pos < current.length() && current != null) {
      //if element is Sequence
      if (current.element instanceof Sequence) {
        Sequence subSequence = new Sequence();
        subSequence = ((Sequence)current.first()).flatten();
        for(;subSequence!=null;subSequence=subSequence.next)
          flattened.add(subSequence.first(), flattened.length());

        /*int x = 0;
        //convert element object to Sequence object 
        inner.flatten();
        Sequence inner = (Sequence)current.element;
        while (x < inner.length() && inner != null) {
          flattened.add(inner.element, flattened.length());
          inner = inner.next;
          x++;*/
        }
      //if element is MyInt or MyChar
      else {
        flattened.add(current.element, flattened.length());
      }
      current = current.next;
      pos++;
    }

    return flattened;
  }

  //deep copy of a sequence object
  public Sequence copy() {

    Sequence copied = this;
    Sequence current = this;
    int pos = 0;
    while (pos < current.length() && current != null) {

      //element is character
      if (current.element instanceof MyChar) {
        MyChar c = new MyChar();
        //MyChar c_elem = (MyChar)current.element;
        c.Set(((MyChar)current.element).Get());
        copied.add(c, copied.length());

      }
      //element is Sequence
      if (current.element instanceof Sequence) {
        copied.add(((Sequence)(current.element)).copy(), copied.length());

      }
      //element is integer
      if (current.element instanceof MyInteger) {
        MyInteger i = new MyInteger();
        i.Set(((MyInteger)current.element).Get());
        //MyChar i_elem = (MyChar)current.element;
        copied.add(i, copied.length());
      }

      current = current.next;
      pos++;
    }

    return copied;

  }


}
