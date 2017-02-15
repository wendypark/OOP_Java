class SequenceIterator extends Sequence {

  private Sequence sequence;
  private SequenceIterator next;

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
    return sequence.GetElement();
  }

  public SequenceIterator advance() {
    this.sequence = this.sequence.GetNext();
  }

  public Boolean equal (SequenceIterator other) {
    return this.sequence == other.sequence;
  }
}