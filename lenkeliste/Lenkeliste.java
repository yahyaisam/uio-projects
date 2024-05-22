public abstract class Lenkeliste<T> implements Liste<T> {

	public class Node {
		public Node neste;
		public T data;

		public Node(T data) {
			this.data = data;
		}
	}

	public Node start;
	public int stoerrelse = 0;

	// Returnerer antall elementer i listen
	public int stoerrelse() {
		return stoerrelse;
	}

	// Legger til et element på slutten av listen
    public void leggTil(T x) {
		if (start == null) {
			start = new Node(x);
		}

		else {
			Node temp = start;
			while (temp.neste != null) {
				temp = temp.neste;
			}
			temp.neste = new Node(x);
		}
		
		stoerrelse++;
	}

	// Returnerer det første elementet i listen (fjernes ikke)
    public T hent() {
        return start.data;
	}

	// Fjerner og returnerer det første elementet i listen
    public T fjern() {
        // Dersom lenkelisten er tom
        if (stoerrelse() == 0) {
            throw new UgyldigListeindeks(0);
        }

        Node temp = start;
        start = start.neste;
        stoerrelse--;

        return temp.data;
	}

	// Returnerer en svarstreng med alle elementer i listen
	@Override
	public String toString() {
		String innhold = "";
		int indeks = 0;

        for (Node temp = start; temp != null; temp = temp.neste) {
            innhold += "Indeks " + indeks + ": " + temp.data + ", ";
			indeks++;
        }
        return innhold;
    }
}
