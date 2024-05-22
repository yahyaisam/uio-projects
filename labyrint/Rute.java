public abstract class Rute {
    protected int radNummer;
    protected int kolonneNummer;
    protected Labyrint labyrint;

    public Rute nord = null;
    public Rute syd = null;
    public Rute vest = null;
    public Rute oest = null;

    public Rute(int radNummer, int kolonneNummer, Labyrint labyrint) {
        this.radNummer = radNummer;
        this.kolonneNummer = kolonneNummer;
        this.labyrint = labyrint;
    }

    public Rute hentNord() {
        return nord;
    }
    
    public Rute hentSyd() {
        return syd;
    }

    public Rute hentVest() {
        return vest;
    }

    public Rute hentOest() {
        return oest;
    }

    public abstract void finn(Rute fra);
}
