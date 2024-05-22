public class Prioritetskoe<T extends Comparable <T>> extends Lenkeliste<T>{
  @Override
  public void leggTil(T x){
    Node pointer = first;
    Node newNode = new Node(x);
    if (size == 0) {
      first = newNode;
      size++;
      return;
    }
    // Gitt at det er flere noder
    for (int i = 0; i < stoerrelse(); i++) {
      int res = x.compareTo(pointer.data);
      if (res > 0) { // x er stoerre enn pointer (, og kommer foran pointer)
        if(pointer.nextNode != null && x.compareTo(pointer.nextNode.data) < 0) { // hvis x er stoerre enn pointer, og pointer har en nesteNode, dvs den ikke er den siste
          newNode.nextNode = pointer.nextNode;
          pointer.nextNode.previousNode = newNode;
          pointer.nextNode = newNode; 
          break;
        } else if (pointer.nextNode == null) { // hvis x er stoerre enn pointer, men pointer har ikke nextNode
          pointer.nextNode = newNode;
          newNode.previousNode = pointer;
          break;
        }
      } else if (res <= 0) {
        if (pointer != first) {
          pointer.previousNode.nextNode = newNode;
          pointer.previousNode = newNode;
        } else {
          first = newNode;
        }
        newNode.nextNode = pointer;
        break;
      }
      pointer = pointer.nextNode;
    }
    size++;
  }
}
