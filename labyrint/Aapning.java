public class Aapning extends HvitRute {
    
    public Aapning(int radNummer, int kolonneNummer, Labyrint labyrint) {
        super(radNummer, kolonneNummer, labyrint);
    }

    public void finn(Rute fra) {
        System.out.println(this.toString());

        return;
    }

    @Override
    public String toString() {
        return "(" + radNummer + "," + kolonneNummer + ")";
    }
}
