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
      this.sequence = new Sequence(new MyChar('E'), null);
      this.isEnd = true;
    } else {
      this.sequence = next;
    }
  }

  public Boolean equal(SequenceIterator other) {
    if (this.isEnd && other.isEnd) {
      return true;
    }

    // For now, the equal only checks if both are isEnd.
    return (this.isEnd == other.isEnd);

    // This doesn't work yet for some reason... not sure why.
    // return this.get() == other.get();
  }

  public Element get() {
    return sequence.element;
  }

  public static SequenceIterator end() {
    SequenceIterator end = new SequenceIterator();
    end.isEnd = true;
    return end;
  }
} 