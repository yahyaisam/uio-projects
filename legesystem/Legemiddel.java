abstract public class Legemiddel {
    protected String navn;
    protected int pris;
    protected static int legemiddelID;
    protected int id;
    protected double virkestoff;

    public Legemiddel(String navn, int pris, double virkestoff) {
        this.navn = navn;
        this.pris = pris;
        this.virkestoff = virkestoff;
        this.legemiddelID++;
        this.id = legemiddelID;
    }
    
    public String hentNavn() {
        return navn;
    }

    public int hentPris() {
        return pris;
    }

    public int hentId(){
        return id;
    }

    public double hentVirkestoff(){
        return virkestoff;
    }
    
    public String toString(){
        return "_______________" + "\nLegemiddel: " + navn + "\nPris: " + pris + "\nID: " + id + "\nVirkestoff: " + virkestoff + "\n_______________";
    }
}
