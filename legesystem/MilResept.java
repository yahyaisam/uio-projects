public class MilResept extends HvitResept {
    
    public MilResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient){
        super(legemiddel, utskrivendeLege, pasient, 3);
    }

    @Override
    public int prisAaBetale() {
       return 0;
    }
}
