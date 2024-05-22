public class IndeksertListe <T> extends Lenkeliste <T> {
    public void sett(int pos, T x){
        if (pos < 0 || pos > stoerrelse() - 1){
            throw new UgyldigListeindeks(pos);
        }
    
        Node pointer = pointer(pos);
        Node newNode = new Node(x);
        
        newNode.previousNode = pointer.previousNode;
        newNode.nextNode = pointer.nextNode;
        if (pointer.nextNode != null){
            pointer.nextNode.previousNode = newNode;
        }
        if (pointer.previousNode != null){
            pointer.previousNode.nextNode = newNode;}
    }
    
    public void leggTil(int pos, T x) { // Legger til en ny node sist
        if (pos < 0 || pos > stoerrelse()){ // Kan ikke vaere under 0, eller stoerre enn antall noder
            throw new UgyldigListeindeks(pos);
        }
        Node newNode = new Node(x); 
        Node pointer = pointer(pos);
    
        if (pos == stoerrelse()) { //Siste elementet i listen
            super.leggTil(x);
            return;
        
        } else if (pointer == null) {// || pos == -1 || pos == 0) { // ny start
            super.leggTil(x);
            return;
            
        } else { // Vi har 2+ noder
            newNode.nextNode = pointer;
            if (pos == 0) {
                first = newNode;
            }
            newNode.previousNode = pointer.previousNode;
            if (pointer.previousNode != null){
            pointer.previousNode.nextNode = newNode;
            }
            pointer.previousNode = newNode;
        }
        size++;
    }

    public T hent(int pos) throws UgyldigListeindeks { //Returnerer dataen i 1. noden
        if (pos < 0 || pos > stoerrelse()){
            throw new UgyldigListeindeks(pos);
        }
        if (pointer(pos) == null){
            return null;
        }
        return pointer(pos).data;
    }


    public T fjern(int pos) {
        Node pointer = pointer(pos);
        if (pos > (stoerrelse() - 1) || pos < 0 || stoerrelse() == 0){ //Hvis det er intet
            throw new UgyldigListeindeks(pos);
        } else if (pointer.previousNode == null && size == 1) { //hvis det bare er en node
            first = null;
            last = null;
        } else if (pointer.nextNode == null) {//Hvis vi er paa siste node
            pointer.previousNode.nextNode = null;
            pointer.previousNode = null;
        } else if (pointer.previousNode == null) {//hvis vi erstatter foerste node
            pointer.nextNode.previousNode = null;
            pointer.nextNode = null;
        } else { // Hvis det er flere noder
            pointer.nextNode.previousNode = pointer.previousNode;
            pointer.previousNode.nextNode = pointer.nextNode;
        }
        size -=1;
        return pointer.data;
    }
}