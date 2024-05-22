public class Stabel<T> extends Lenkeliste<T> {

    @Override
    public void leggTil(T x) {
        if (start == null) {
            start = new Node(x);
        }

        else {
            Node nyNode = new Node(x);
            nyNode.neste = start;
            start = nyNode;
        }
        stoerrelse++;
	}
}
