public class Prioritetskoe<T extends Comparable<T>> extends Lenkeliste<T> {

    // Setter inn elementer i en sortert rekkefølge der elementene er stigende
    @Override
    public void leggTil(T x) {
        if (stoerrelse() == 0) {
            super.leggTil(x);

            return;
        }

        if (x.compareTo(start.data) < 0) {
            Node temp = start;
            
            Node nyNode = new Node(x);
            start = nyNode;
            nyNode.neste = temp;

            stoerrelse++;
            return;
        }

        // Siden jeg ikke har en slutt-peker i Lenkeliste, er jeg nødt til å finne det siste elementet
        // ved å loope gjennom, selv om dette ikke er en effektiv måte å gjøre det på
        Node slutt = start;
        while (slutt.neste != null) {
            slutt = slutt.neste;
        }
        if (x.compareTo(slutt.data) > 0) {
            super.leggTil(x);

            return;
        }

        Node temp = start;
        for (int i = 0; i < stoerrelse() - 1; i++) {
            if (temp.data.compareTo(x) <= 0) {
                temp = temp.neste;
            }
        }
        
        Node nyNode = new Node(x);
        nyNode.neste = temp.neste;
        temp.neste = nyNode;

        stoerrelse++;
    }

    // Returnerer det minste elementet
    public T hent() {
        return super.hent();
    }

    // Fjerner og returnerer det minste elementet
    public T fjern() {
        return super.fjern();
    }
}
