public class HvitResept extends Resept {
    protected String farge = "Hvit";
    public HvitResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit){
        super(legemiddel, utskrivendeLege, pasient, reit);
    }

    @Override
    public String farge() {
        return farge;
    }
    @Override
    public int prisAaBetale() {
        return this.legemiddel.pris;
    }
    public String toString(){
        return "_______________" + "\nResepttype: " + farge + "\nReseptID: " + id + "\nLegemiddel: " + legemiddel.navn + "\nPris: " + prisAaBetale() + "\nLege: " + lege.navn + "\nPasient: " + pasient + "\nReit: " + reit + "\n_______________";
    }
}