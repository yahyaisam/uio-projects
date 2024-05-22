public class Koe <T> extends Lenkeliste <T> {
    @Override
    public void leggTil(T x) { // Legger til en ny node paa slutten
        Node newNode = new Node(x);
        
        if (first == null) {
            first = newNode;
            last = first;

        } else {
            last.nextNode = newNode;
            newNode.previousNode = last;
            last = newNode;
        }
        size++;
       
    }

    @Override
    public T fjern() throws UgyldigListeindeks { //Skal fjerne det foerste elementet i listen og returnere det neste nye

        if (stoerrelse() == 0){ // Ingenting aa fjerne
            throw new UgyldigListeindeks(0);
        }
        // 
        T toBeReturned = first.data; 
        if (first.nextNode == null) {
            first = null;
            size -=1;
        } else {
            first = first.nextNode;
            first.previousNode = null;
            size -=1;
        }
        return toBeReturned;
    }
}
