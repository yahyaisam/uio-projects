public class PResept extends HvitResept {
    public PResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient){
        super(legemiddel, utskrivendeLege, pasient, 3);
    }

    @Override
    public int prisAaBetale() {
       return (int) Math.max((this.legemiddel.pris -= 108), 0);
    }
}
