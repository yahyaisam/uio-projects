public class HvitRute extends Rute {

    public HvitRute(int radNummer, int kolonneNummer, Labyrint labyrint) {
        super(radNummer, kolonneNummer, labyrint);
    }

    @Override
    public String toString() {
        return ".";
    }

    public void finn(Rute fra) {
        if (nord != null && nord != fra) {
            nord.finn(this);
        }

        if (syd != null && syd != fra) {
            syd.finn(this);
        }

        if (vest != null && vest != fra) {
            vest.finn(this);
        }

        if (oest != null && oest != fra) {
            oest.finn(this);
        }
    }
}
