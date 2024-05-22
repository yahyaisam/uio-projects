public class Lege implements Comparable<Lege>{
    protected int antallNarkotiskSkrevet = 0;
    protected String navn;
    protected IndeksertListe<Resept> utskrevneResepter = new IndeksertListe<>();
    

    public Lege(String navn){
        this.navn = navn;
    }

    public String hentNavn(){
        return this.navn;
    }

    @Override
    public String toString(){
        return "Utskrivende lege: " + hentNavn();
    }

    public IndeksertListe<Resept> hentUtskrevneResepter(){
        return utskrevneResepter;
    }

    @Override
    public int compareTo(Lege annen) {
        if (navn.compareTo(annen.navn) > 0){
            return 1;
        }

        if (navn.compareTo(annen.navn) < 0){
            return -1;
        }

        return 0;
    }
    
    public HvitResept skrivHvitResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        if (legemiddel instanceof Narkotisk) {
            throw new UlovligUtskrift(this, legemiddel);
        }

        HvitResept hvitResept = new HvitResept(legemiddel, this, pasient, reit);
        utskrevneResepter.leggTil(hvitResept);
        pasient.leggTilResept(hvitResept);

        return hvitResept;
    }

    public MilResept skrivMilResept(Legemiddel legemiddel, Pasient pasient) throws UlovligUtskrift {
        if (legemiddel instanceof Narkotisk) {
            throw new UlovligUtskrift(this, legemiddel);
        }

        MilResept milResept = new MilResept(legemiddel, this, pasient);
        utskrevneResepter.leggTil(milResept);
        pasient.leggTilResept(milResept);

        return milResept;
    }

    public PResept skrivPResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        if (legemiddel instanceof Narkotisk) {
            throw new UlovligUtskrift(this, legemiddel);
        }

        PResept PResept = new PResept(legemiddel, this, pasient);
        utskrevneResepter.leggTil(PResept);
        pasient.leggTilResept(PResept);

        return PResept;
    }
    
    public BlaaResept skrivBlaaResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        if (legemiddel instanceof Narkotisk && !(this instanceof LegeSpesialist)) {
            throw new UlovligUtskrift(this, legemiddel);
        }
    
        BlaaResept blaaResept = new BlaaResept(legemiddel, this, pasient, reit);
        if (blaaResept.farge().equals("Blaa")){
            antallNarkotiskSkrevet++;
        }
        utskrevneResepter.leggTil(blaaResept);
        pasient.leggTilResept(blaaResept);

        return blaaResept;
    }
}
