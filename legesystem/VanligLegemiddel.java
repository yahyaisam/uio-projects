public class VanligLegemiddel extends Legemiddel {
    public VanligLegemiddel(String navn, int pris, double virkestoff) {
        super(navn, pris, virkestoff);
    }

    public String hentNavn() {
        return navn;
    }

    public int hentPris() {
        return pris;
    }

    public int hentId() {
        return id;
    }

    public double hentVirkestoff() {
        return virkestoff;
    }
}