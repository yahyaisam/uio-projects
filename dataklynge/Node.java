public class Node {
	private int antallProsessorer;
	private int minne;

	public Node(int antallProsessorer, int minne) {
		this.antallProsessorer = antallProsessorer;
		this.minne = minne;
	}

	public int antProsessorer() {
		return this.antallProsessorer;
	}

	public boolean nokMinne(int paakrevdMinne) {
		if (this.minne >= paakrevdMinne) {
			return true;
		}

		return false;
	}
}
