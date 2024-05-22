public class IndeksertListe<T> extends Lenkeliste<T> {

    // Setter x inn i posisjon (indeks) pos
    public void leggTil(int pos, T x) {
        if (pos < 0 || pos > stoerrelse()) {
            throw new UgyldigListeindeks(pos);
        }

        Node nyNode = start;
        if (pos == 0) {
            start = new Node(x);
            start.neste = nyNode;

            stoerrelse++;
            return;
        }
        
        if (pos == stoerrelse()) {
            super.leggTil(x);

            return;
        }

        Node temp = start;
        for (int i = 0; i < pos - 1; i++) {
            temp = temp.neste;
        }
        Node ny = new Node(x);
        ny.neste = temp.neste;
        temp.neste = ny;
        
        stoerrelse++;
    }

    // Erstatter elementet i posisjon pos med x
    public void sett(int pos, T x) {
        if (pos < 0 || pos >= stoerrelse()) {
            throw new UgyldigListeindeks(pos);
        }

        Node temp = start;
        for (int i = 0; i < pos; i++) {
            temp = temp.neste;
        }
        temp.data = x;
    }

    // Returnerer (fjerner ikke) elementet på posisjon pos
    public T hent(int pos) {
        if (pos < 0 || pos > stoerrelse()) {
            throw new UgyldigListeindeks(pos);
        }

        if (pos == 0) {
            return start.data;
        }

        if (start == null) {
            throw new UgyldigListeindeks(0);
        }

        Node temp = start;
        for (int i = 0; i < pos; i++) {
            temp = temp.neste;
        }

        return temp.data;
    }

    // Fjerner og returnerer elementet på posisjon posisjon pos
    public T fjern(int pos) {
        if (pos < 0 || pos >= stoerrelse()) {
            throw new UgyldigListeindeks(pos);
        }

        if (stoerrelse() == 0) {
            throw new UgyldigListeindeks(0);
        }

        if (pos == 0) {
            fjern();
        }

        Node temp = start;
        for (int i = 0; i < pos - 1; i++) {
            if (temp.neste == null) {
                throw new UgyldigListeindeks(i);
            }
            temp = temp.neste;
        }

        T returVerdi = temp.neste.data;
        temp.neste = temp.neste.neste;

        stoerrelse--;
        return returVerdi;
    }
}
