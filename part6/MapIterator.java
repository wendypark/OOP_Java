public class MapIterator {

  Map map;
  private Boolean isEnd = false;

  public MapIterator() {
    this.map = null;
  }

  public MapIterator(Map map) {
    this.map = map;
  }

  public void advance() {
    Map next = this.map.next;
    if (next == null) {
      this.map = createEndMap();
      this.isEnd = true;
    } else {
      this.map = next;
    }
  }

  public Boolean equal(MapIterator other) {
    if (this.isEnd && other.isEnd) {
      return true;
    }

    // I THINK this works...
    return this.get().value == other.get().value;
  }

  public Pair get() {
    return map.pair;
  }

  public void set(Pair inPair) {
    map.pair = inPair;
  }

  private static Map createEndMap() {
    return new Map(new Pair(new MyChar('E'), new MyChar('E')), null);
  }

  public static MapIterator end() {
    MapIterator end = new MapIterator(MapIterator.createEndMap());
    end.isEnd = true;
    return end;
  }
} 