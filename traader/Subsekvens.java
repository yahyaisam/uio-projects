public class Subsekvens {
    public final String subsekvens;
    private int antall; // Antall forekomster av en gitt subsekvens hos flere personer

    public Subsekvens(String subsekvens, int antall) {
        this.subsekvens = subsekvens;
        this.antall = antall;
    }

    public int hentAntall() {
        return antall;
    }

    public void oekAntall() {
        antall++;
    }

    public void oekAntall(int nye) {
        antall += nye;
    }

    @Override
    public String toString() {
        return "(" + subsekvens + "," + antall + ")";
    }
}
