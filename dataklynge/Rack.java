public class Rack {
	// Velger array fremfor ArrayList fordi vi vet at vi kun har behov for 12 plasser,
	// og arrayer har en fixed lengde
	private Node[] noder;
	private int antallNoder = 0;

	public Rack() {
		this.noder = new Node[12];
	}

	public void settInn(Node node) {
		if (antallNoder < 12) {
			noder[antallNoder] = node;

			antallNoder++;
		}
		
		else {
			System.out.println("Det er ikke plass til flere noder.");
		}
	}

	public int antNoder() {
		return this.antallNoder;
	}

	public int antProsessorer() {
		int antPros = 0;

		for (int i = 0; i < noder.length; i++) {
			if (noder[i] != null) {
				antPros += noder[i].antProsessorer();
			}
		}

		return antPros;
	}

	public int noderMedNokMinne(int paakrevdMinne) {
		int antNoder = 0;

		for (int i = 0; i < noder.length; i++) {
			if (noder[i] != null) {
				if (noder[i].nokMinne(paakrevdMinne) == true) {
					antNoder++;
				}
			}
		}

		return antNoder;
	}

	public boolean erFull() {
		if (antallNoder == 2) { // TODO: husk å bytte tilbake til 12
			return true;
		}

		return false;
	}
}
