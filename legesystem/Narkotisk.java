public class Narkotisk extends Legemiddel {
    protected int styrke;
    public Narkotisk(String navn, int pris, int narkotiskStyrke, double virkestoff) {
        super(navn, pris, virkestoff);
        this.styrke = narkotiskStyrke;
    }

    public int hentNarkotiskStyrke(){
        return styrke;
    }
    
    public String toString(){
        return "_______________" + "\nLegemiddel: " + navn + "\nType: Narkotisk" +  "\nPris: " + pris + "\nID: " + id + "\nVirkestoff: " + virkestoff + "\nNarkotisk styrke: " + styrke + "\n_______________";
    }
}
