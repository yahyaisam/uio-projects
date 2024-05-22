import java.lang.Math;

public class BlaaResept extends Resept {
    protected String farge = "Blaa";
    public BlaaResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit){
        super(legemiddel, utskrivendeLege, pasient, reit);
        //this.legemiddel.pris = (int) Math.rint(this.legemiddel.pris*0.25);
    }
    
    @Override
    public String farge() {
       return farge;
    }
    @Override
    public int prisAaBetale() {
       return (int) Math.rint(this.legemiddel.pris*0.25);
    }

    public String toString(){
        return "_______________" + "\nResepttype: " + farge + "\nReseptID: " + id + "\nLegemiddel: " + legemiddel.navn + "\nPris: " + prisAaBetale() + "\nLege: " + lege.navn + "\nPasient: " + pasient + "\nReit: " + reit + "\n_______________";
    }
}
