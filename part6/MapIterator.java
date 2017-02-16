public class MapIterator extends Map {
  private Map map;
  private MapIterator next;
  //initialize to empty set 
  public MapIterator(){
    map = null;
    next = null; 
  }

  //Get() and Set()
  public MapIterator(Map map) {
    this.map = map;
  }

  public Pair get() {
    return map.get_pair();
  }

  public Element get_value() {
    return map.get_value();
  }

  public MyChar get_key() {
    return map.get_key();
  }

  public void advance() {
    Map next = this.map.get_next();
    if (next == null) {
      this.map = this.map.end();
    } else {
      this.map = next;
    }
  }

  public Boolean equal (MapIterator other) {
    if ((this.get_value() instanceof End) && (other.get_value() instanceof End)){
      return true;
    }
    return this.get_pair().equal(other.get_pair());
  }
} 