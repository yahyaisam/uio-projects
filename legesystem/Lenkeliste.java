import java.util.Iterator;

abstract class Lenkeliste <T> implements Liste<T> {
    protected class Node{
        T data; 
        Node previousNode; 
        Node nextNode; 
        public Node(T data){
            this.data = data;
        }
        public String toString(){
            return data.toString();
        }
    }

    protected int size = 0;
    protected Node first;
    protected Node last;

    @Override
    public int stoerrelse() {
        return size;
    }

    @Override
    public void leggTil(T x) { // Legger til en ny node paa slutten
        Node newNode = new Node(x);
        
        if (last == null) { //Da er lenkelisten tom
            first = newNode;
            last = newNode;
        } else {
            newNode.previousNode = last; // 1+ elements in list
            last.nextNode = newNode;
        }
        size++;
        last = newNode;
    }

    @Override
    public T fjern() throws UgyldigListeindeks { //Skal fjerne det foerste elementet i listen og returnere det neste nye
        if (stoerrelse() == 0){ // Ingenting aa fjerne
            throw new UgyldigListeindeks(0);
        }
        T toBeReturned = first.data; 
        if (first.nextNode == null) { //Hvis stoerrelse == 1
            first = null;
            
        } else if (stoerrelse() >= 2) { // Hvis stoerrelse >=2
            first.nextNode.previousNode = null;
            first = first.nextNode;
        }
        size -=1;
        return toBeReturned;
    }

    @Override
    public T hent()  { //Returnerer data til den foerste noden
        return first.data;
    }

    public Node pointer(int pos){
        Node currentNode = first;
        if (pos > stoerrelse() || pos < 0) { //hvis ikke mulig
            return null;
            }

        else {
            for (int i = 0; i<pos; i++) {
                currentNode = currentNode.nextNode;
            }
        }
        return currentNode;
    }
    
    public String toString(){
        Node currentNode = first;
        int index = 0;
        String theFinestString = "";
        while (currentNode.nextNode != null){
            theFinestString += ("\nNode #" + index + ": " + currentNode.data);
            index++;
            currentNode = currentNode.nextNode;
        }
        return theFinestString;
    }

    @Override
    public Iterator<T> iterator() {
        return new LenkelisteIterator();
    }
    
   
    protected class LenkelisteIterator implements Iterator<T>{
        private Node cur = first;
        
       @Override
        public boolean hasNext(){
            if (cur != null){
                return true;
            }
            return false;
        }
        @Override
        public T next(){
            
            T elem = cur.data;
            cur = cur.nextNode;

            return elem;
        }
    }
}
