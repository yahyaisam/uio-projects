public class Stabel <T> extends Lenkeliste <T> {
    @Override
    public void leggTil(T x) {
        Node newNode = new Node(x);
        size++;
        if (first == null) {
            last = newNode;
            first = newNode;
            return;
        }
        first.previousNode = newNode;
        newNode.nextNode = first;
        first = newNode;
    }
}
