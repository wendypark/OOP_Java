public class SequenceIterator {

  Sequence sequence;
  private Boolean isEnd = false;


  //initialize to empty set 
  public SequenceIterator(){
    sequence = null;
  }

  //Get() and Set()
  public SequenceIterator(Sequence sequence) {
    this.sequence = sequence;
  }

  public void advance() {
    Sequence next = this.sequence.next;
    if (next == null) {
      this.sequence = createEndSequence();
      this.isEnd = true;
    } else {
      this.sequence = next;
    }
  }

  public Boolean equal(SequenceIterator other) {
    if (this.isEnd && other.isEnd) {
      return true;
    }

    // I THINK this works...
    return this.get() == other.get();
  }

  public Element get() {
    return sequence.element;
  }

  public void set(Element elem) {
    sequence.element = elem;
  }

  private static Sequence createEndSequence() {
    return new Sequence(new MyChar('E'), null);
  }

  public static SequenceIterator end() {
    SequenceIterator end = new SequenceIterator(createEndSequence());
    end.isEnd = true;
    return end;
  }
} 