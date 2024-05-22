abstract class Resept {
    protected static int reseptID;
    protected Legemiddel legemiddel;
    protected Lege lege;
    protected Pasient pasient;
    protected int reit;
    protected int id;

    public Resept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        this.legemiddel = legemiddel;
        this.lege = utskrivendeLege;
        this.pasient = pasient;
        this.reit = reit;
        this.reseptID++;
        this.id = reseptID;
    }

    public Legemiddel hentLegemiddel() {
        return legemiddel;
    }

    public int hentReseptID() {
        return reseptID;
    }

    public Pasient hentPasient() {
        return pasient;
    }

    public int hentReit() {
        return reit;
    }

    public boolean bruk() {
        if (reit > 0){
            reit-=1;
            return true;
        }
        else {
            return false;
        }
    }

    public String hentLegeNavn() {
        return lege.hentNavn();
    }

    abstract public String farge();
    abstract public int prisAaBetale();

    public String toString(){
        return "Legemiddel: " + legemiddel.navn + "\nReseptID: " + id + "\nLege: " + lege.navn + "\nPasient: " + pasient + "\nReit: " + reit;
    }
}
