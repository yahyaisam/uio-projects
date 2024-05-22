public class SortRute extends Rute {
    
    public SortRute(int radNummer, int kolonneNummer, Labyrint labyrint) {
        super(radNummer, kolonneNummer, labyrint);
    }

    @Override
    public String toString() {
        return "#";
    }

    public void finn(Rute fra) {
        if (fra == null) {
            System.out.println("Kan ikke starte i sort rute.");
        }
        
        return;
    }
}
