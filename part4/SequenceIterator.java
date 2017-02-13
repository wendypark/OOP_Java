class SequenceIterator {

  private Sequence sequence;

  //initialize to empty set 
  public SequenceIterator(){
    sequence = null;
    next = null; 
  }

  //Get() and Set()
  public SequenceIterator(Sequence sequence) {
    this.sequence = sequence;
  }

  public Element get() {
    return sequence.element;
  }

  public SequenceIterator advance() {
    this.sequence = this.sequence.next;
  }

  public bool equal (SequenceIterator other) {
    return this.sequence == other.sequence
  }
}