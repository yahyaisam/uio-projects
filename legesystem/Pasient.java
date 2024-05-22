public class Pasient {
    protected String navn;
    protected String foedselsnummer;
    protected IndeksertListe<Resept> reseptListe = new IndeksertListe<>();
    private static int idCounter = 0;
    protected int unikID;

    public Pasient(String navn, String foedselsnummer) {
        this.navn = navn;
        this.foedselsnummer = foedselsnummer;
        idCounter++;
        this.unikID = idCounter;
    }

    public void leggTilResept(Resept resept) {
        reseptListe.leggTil(resept);
    }

    public IndeksertListe<Resept> hentReseptListe() {
        return reseptListe;
    }

    public String hentNavn() {
        return this.navn;
    }

    public String hentFoedselsnummer() {
        return foedselsnummer;
    }

    public int hentId(){
        return unikID;
    }

    @Override
    public String toString() {
        return hentNavn() + ", foedselsnummer: " + hentFoedselsnummer();
    }
}
