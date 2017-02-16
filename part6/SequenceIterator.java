public class SequenceIterator extends Sequence {
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
    return sequence.get_element();
  }

  public void advance() {
    Sequence next = this.sequence.get_next();
    if (next == null) {
      this.sequence = new Sequence(new End(), null);
    } else {
      this.sequence = next;
    }
  }

  public Boolean equal (SequenceIterator other) {
    if ((this.get() instanceof End) && (other.get() instanceof End)){
      return true;
    }
    return this.get() == other.get();
  }
} 